package com.TrackrMan;

import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrackViewController implements Initializable {

    public TrackViewController() {

        try {
            trackingList = new TrackCollection();
            trackingList.loadList();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unsaved");
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
    private MenuItem selectedRemoveFromList;
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
                Parcel newItem;
                switch (vendorOption.getSelectionModel().getSelectedIndex() ){
                    case 0 :
                        newItem = new ThaiPostParcel(newTrackName,newTrackCode);
                        newItem.trackThis();
                        trackingList.getTrackingList().add(newItem);
                        break;
                    case 1 :
                        newItem = new KerryParcel(newTrackName,newTrackCode);
                        newItem.trackThis();
                        trackingList.getTrackingList().add(newItem);
                        break;
                    case 2 :
                        newItem = new DHLParcel(newTrackName,newTrackCode);
                        newItem.trackThis();
                        trackingList.getTrackingList().add(newItem);
                        break;
                    default:
                        break;
                }
            }

            trackingList.getNumOfTrackingList().set(trackingList.getTrackingList().size());
            inputCodeField.setText("");
//            AlertBox.LoadingWindow();
            try {
                trackingList.saveList();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    System.out.println("Double Clicked");
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

}
