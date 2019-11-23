package com.TrackrMan;

import com.jfoenix.controls.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackPane implements Initializable {

    public TrackPane() {
        //Pull off List of Data Every Time on Launch

        //Create Array (Obserable)
        trackingNowList = FXCollections.observableArrayList();

        //Add Listener when number of items is updated
        numOfTrackingList.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > 0){
                emptyMessage.setVisible(false);
            }
            else{
                emptyMessage.setVisible(true);
            }
        });

    }

    //Number of Items
    IntegerProperty numOfTrackingList = new SimpleIntegerProperty(0);

    //List for ListView to Show
    ObservableList<Parcel> trackingNowList;

    //FXML UI Linking Block Start
    @FXML
    private JFXTextField inputCodeField;

    @FXML
    private JFXComboBox vendorOption;
    private ObservableList<String> vendorList = FXCollections.observableArrayList("Thailand Post","Kerry Express", "DHL");
    private String[] vendorColor = {"#ed1c24","#e96514","#fc0"};

    @FXML
    private JFXButton addToTrack;

    @FXML
    private JFXListView<Parcel> trackingListView;

    @FXML
    private HBox emptyMessage;

    @FXML
    private MenuItem selectedRemoveFromList;
    //FXML UI Linking Block End

    //When click on Add Event Handler Block Start
    public void clickAdd() {
        String newTrackCode = inputCodeField.getText();


        if ( (newTrackCode.trim()).equals("") ) {
            System.out.println("Empty Box");
            AlertBox.ErrorMsgNoReply("Error!","Please enter Tracking Code.");
        }
        else{
            Parcel newItem;
            String newTrackName = AlertBox.AskForNaming("Before Continue", "Do you want to add a name for your parcel?");
            switch (vendorOption.getSelectionModel().getSelectedIndex() ){
                case 0 :
                    newItem = new ThaiPostParcel(newTrackName,newTrackCode);
                    trackingNowList.add(newItem);
                    break;
                case 1 :
                    newItem = new KerryParcel(newTrackName,newTrackCode);
                    trackingNowList.add(newItem);
                    break;
                case 2 :
                    newItem = new DHLParcel(newTrackName,newTrackCode);
                    trackingNowList.add(newItem);
                    break;
                default:
                    break;
            }
            numOfTrackingList.set(trackingNowList.size());
            inputCodeField.setText("");
        }
    }
    //When click on Add Event Handler Block Start

    //Initialization when view is added
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set Vendor Option to Choice 1
        vendorOption.setItems(vendorList);
        vendorOption.setValue(vendorList.get(0));

        //Set Add Button Style and Action
        addToTrack.setStyle("-fx-background-color:" + vendorColor[0] + ";" );
        addToTrack.setOnAction(event -> { clickAdd(); });

        //Set ListView to Show Items from List, Set Appearance of List Cell
        trackingListView.setItems(trackingNowList);
        trackingListView.setCellFactory(param -> new ParcelCell());
        trackingListView.setEditable(true);

        //Reload Number of List
        numOfTrackingList.set(trackingNowList.size());

        //When click to change Vendor Event Handler Block Start
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
        //When click to change Vendor Event Handler Block End

        //Set Image within ContextMenu
        Image trashImage = new Image(getClass().getResourceAsStream("/img/icons8-filled_trash.png"),20,20,true,true);
        ImageView trashImageView = new ImageView(trashImage);
        selectedRemoveFromList.setGraphic(trashImageView);

        //When click to remove from List Event Handler Block Start
        selectedRemoveFromList.setOnAction(event -> {
            trackingNowList.remove(trackingListView.getSelectionModel().getSelectedIndex());
            trackingListView.setItems(trackingNowList);
            numOfTrackingList.set(trackingNowList.size());

        });
        //When click to remove from List Event Handler Block Start
    }

}
