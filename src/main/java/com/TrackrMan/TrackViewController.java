package com.TrackrMan;

import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrackViewController implements Initializable {

    public TrackViewController() {

        try {
            trackingList = new TrackCollection();
            trackingList.loadList();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load from File Unsuccessful: Created New File");
            trackingList = new TrackCollection();
        }

        //Add Listener when number of items is updated
        trackingList.getNumOfTrackingList().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > 0){
                emptyMessage.setVisible(false);
            }
            else{
                emptyMessage.setVisible(true);
            }
        });
        AppRunner.trackCollection = trackingList;
    }

    // Attribute Block START
        // List for ListView to Show
    private TrackCollection trackingList;

        // FXML UI Linking Block Start
    @FXML
    private JFXListView<Parcel> trackingListView;

    @FXML
    private JFXTextField inputCodeField;

    @FXML
    private JFXComboBox vendorOption;
    private ObservableList<String> vendorList = FXCollections.observableArrayList("Thailand Post","Kerry Express", "DHL");
    private String[] vendorColor = {"#ed1c24","#e96514","#fc0"};

    @FXML
    private JFXButton addToTrack;

    @FXML
    private HBox emptyMessage;

    @FXML
    private MenuItem selectedRetrackFromList,selectedMoreDetailFromList,selectedRemoveFromList;
        // FXML UI Linking Block End

    // Attribute Block START

    //When click on Add Event Handler Block
    public void clickAdd() {
        String newTrackCode = inputCodeField.getText();

        if ( (newTrackCode.trim()).equals("") ) {
            AlertBox.ErrorMsgNoReply("Error!","Please enter Tracking Code.");
        }
        else{
            String newTrackName = AlertBox.AskForNaming("Before Continue", "Do you want to add a name for your parcel?");
            if (newTrackName != null) {
                Parcel newItem = null;
                switch (vendorOption.getSelectionModel().getSelectedIndex() ){
                    case 0 :
                        newItem = new ThaiPostParcel(newTrackName,newTrackCode);
                        break;
                    case 1 :
                        newItem = new KerryParcel(newTrackName,newTrackCode);
                        break;
                    case 2 :
                        newItem = new DHLParcel(newTrackName,newTrackCode);
                        break;
                    default:
                        break;
                }
                assert newItem != null;
                if (newItem.trackThis()) { trackingList.getTrackingList().add(newItem); }
                else { AlertBox.ErrorMsgNoReply("Error!","Please check your code and try again."); }
            }
            trackingList.getNumOfTrackingList().set(trackingList.getTrackingList().size());
            inputCodeField.setText("");
            try { trackingList.saveList(); }
            catch (IOException e) { e.printStackTrace(); }
            AlertBox.answer = null;
        }
    }

    //Initialization when view is added
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set Vendor Option to Choice 1
        vendorOption.setItems(vendorList);
        vendorOption.setValue(vendorList.get(0));

        // Set Add Button Style and Action
        addToTrack.setStyle("-fx-background-color:" + vendorColor[0] + ";" );
        addToTrack.setOnAction(event -> { clickAdd(); });

        // Set ListView to Show Items from List, Set Appearance of List Cell
        trackingListView.setItems(trackingList.getTrackingList());
        trackingListView.setCellFactory(param -> new ParcelListCell());

        // Reload Number of List
        trackingList.getNumOfTrackingList().set(trackingList.getTrackingList().size());

        // When Double Click on List
        trackingListView.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 ) {
                if ( trackingListView.getSelectionModel().getSelectedIndex() >= 0) {
                    this.createMoreDetailWindow( (trackingList.getTrackingList().get(trackingListView.getSelectionModel().getSelectedIndex())) );
                }
                else {
                    trackingListView.setFocusTraversable(false);
                }
            }
        });

        // When click to change Vendor Event Handler Block
        vendorOption.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {

            switch (vendorList.indexOf(newValue)){
                case 0:
                    inputCodeField.setStyle("-jfx-focus-color: #ed1c24;");
                    addToTrack.setStyle("-fx-background-color:" + vendorColor[vendorList.indexOf(newValue)] + ";" );
                    break;
                case 1:
                    inputCodeField.setStyle("-jfx-focus-color: #e96514;");
                    addToTrack.setStyle("-fx-background-color:" + vendorColor[vendorList.indexOf(newValue)] + ";" );
                    break;
                case 2:
                    inputCodeField.setStyle("-jfx-focus-color: #c17900;");
                    addToTrack.setStyle("-fx-background-color:" + vendorColor[vendorList.indexOf(newValue)] + ";" + "-fx-text-fill: black;");
                    break;
            }

        });

        // Set Image within ContextMenu
        Image reloadImage = new Image(getClass().getResourceAsStream("/img/icons8-synchronize.png"),20,20,true,true);
        ImageView reloadImageView = new ImageView(reloadImage);
        selectedRetrackFromList.setGraphic(reloadImageView);
        selectedRetrackFromList.setOnAction(event -> {
            if ( trackingListView.getSelectionModel().getSelectedIndex() >= 0) {
                trackingList.getTrackingList().get(trackingListView.getSelectionModel().getSelectedIndex()).trackThis();
            }
        });

        Image moreDetailImage = new Image(getClass().getResourceAsStream("/img/icons8-more_filled.png"),20,20,true,true);
        ImageView moreDetailImageView = new ImageView(moreDetailImage);
        selectedMoreDetailFromList.setGraphic(moreDetailImageView);
        selectedMoreDetailFromList.setOnAction(event -> {
            if ( trackingListView.getSelectionModel().getSelectedIndex() >= 0) {
                this.createMoreDetailWindow( (trackingList.getTrackingList().get(trackingListView.getSelectionModel().getSelectedIndex())) );
            }
        });

        Image trashImage = new Image(getClass().getResourceAsStream("/img/icons8-filled_trash.png"),20,20,true,true);
        ImageView trashImageView = new ImageView(trashImage);
        selectedRemoveFromList.setGraphic(trashImageView);
            //When click to remove from List Event Handler Block
        selectedRemoveFromList.setOnAction(event -> {
            Boolean confirmBool = AlertBox.AskForConfirm("Confirmation", "Are you sure you want to remove this?");
            if (confirmBool) {
                trackingList.getTrackingList().remove(trackingListView.getSelectionModel().getSelectedIndex());
                trackingListView.setItems(trackingList.getTrackingList());
                trackingList.getNumOfTrackingList().set(trackingList.getTrackingList().size());
                try { trackingList.saveList(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        });

        // Auto Capitalize in TextField
        inputCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            inputCodeField.setText(newValue.toUpperCase());
        });

        // When pressed Enter on Textfield
        inputCodeField.setOnKeyPressed(event -> {
            if ( (event.getCode()).equals(KeyCode.ENTER) ) {
                clickAdd();
            }
        });
    }

    public void createMoreDetailWindow(Parcel parcel) {

        Stage window;
        Scene scene;
        VBox view1;
        HBox upperView,lowerView;
        TableView<StatusParcel> tableView = new TableView<>();
        ObservableList<StatusParcel> dataTable = FXCollections.observableArrayList();

        String getJSON = parcel.getJsonString();
        // Convert to JSONObject to Get Status
        JSONObject realJSON = new JSONObject(getJSON);
        JSONObject responseJSON = realJSON.getJSONObject("response");
        JSONObject itemsJSON = responseJSON.getJSONObject("items");
        // API can do multiple items but we need only one
        JSONArray itemStatusArray = itemsJSON.getJSONArray(parcel.getTrackCode());

        for (int i = 0 ; i < itemStatusArray.length() ; i++) {
            JSONObject foo = itemStatusArray.getJSONObject(i);
            dataTable.add( new StatusParcel(foo.get("status_date").toString(), foo.get("status_description").toString() , foo.get("location").toString() ) );
        }

        window = new Stage();
        window.setTitle(parcel.getName() + " Information");
        window.setResizable(false);

        Label itemTopic = new Label(parcel.getName() + " : " + parcel.getTrackCode());
        itemTopic.setFont(new Font(18));
        upperView = new HBox(itemTopic);
        upperView.setAlignment(Pos.CENTER);
        upperView.setPadding(new Insets(20));

        TableColumn<StatusParcel,String> dateCol = new TableColumn<>("Date/Time");
        dateCol.setMinWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateStatus"));

        TableColumn<StatusParcel,String> statusCol = new TableColumn<>("Status");
        statusCol.setMinWidth(150);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<StatusParcel,String> locationCol = new TableColumn<>("Location");
        locationCol.setMinWidth(250);
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableView.setItems(dataTable);
        tableView.getColumns().addAll(dateCol,statusCol,locationCol);
        tableView.setEditable(false);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {window.close();});
        closeButton.setPadding(new Insets(10,50,10,50));
        lowerView = new HBox(closeButton);
        lowerView.setAlignment(Pos.CENTER);
        lowerView.setPadding(new Insets(20));

        view1 = new VBox(upperView,tableView,lowerView);

        scene = new Scene(view1,600,400);
        window.setScene(scene);
        window.show();

    }

}
