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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackPane implements Initializable {

    public TrackPane() {

        trackingNowList = FXCollections.observableArrayList();

        trackingNowList.add(
                new ThaiPostParcel("ASD","DSA"));

        numOfTrackingList.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > 0){
                emptyMessage.setVisible(false);
            }
            else{
                emptyMessage.setVisible(true);
            }
        });

    }

    IntegerProperty numOfTrackingList = new SimpleIntegerProperty(0);

    ObservableList<Parcel> trackingNowList;

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

    private AnchorPane cellLoader;

    public void clickAdd() {
        String newTrackCode = inputCodeField.getText();
        String newTrackName = "";

        switch (vendorOption.getSelectionModel().getSelectedIndex() ){
            case 0 :
                Parcel newItem = new ThaiPostParcel(newTrackName,newTrackCode);
                trackingNowList.add(newItem);
                break;
            default:
                break;
        }
        numOfTrackingList.set(trackingNowList.size());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vendorOption.setItems(vendorList);
        vendorOption.setValue(vendorList.get(0));
        addToTrack.setStyle("-fx-background-color:" + vendorColor[0] + ";" );
        addToTrack.setOnAction(event -> { clickAdd(); });

        trackingListView.setItems(trackingNowList);
        trackingListView.setCellFactory(param -> new ParcelCell());
        trackingListView.setEditable(true);

        numOfTrackingList.set(trackingNowList.size());

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

        Image trashImage = new Image(getClass().getResourceAsStream("img/icons8-filled_trash.png"),20,20,true,true);
        ImageView trashImageView = new ImageView(trashImage);
        selectedRemoveFromList.setGraphic(trashImageView);

        selectedRemoveFromList.setOnAction(event -> {
            trackingNowList.remove(trackingListView.getSelectionModel().getSelectedIndex());
            trackingListView.setItems(trackingNowList);
            numOfTrackingList.set(trackingNowList.size());

        });
    }

}
