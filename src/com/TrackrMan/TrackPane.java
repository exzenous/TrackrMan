package com.TrackrMan;

import com.jfoenix.controls.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackPane implements Initializable {

    IntegerProperty numOfTrackingList = new SimpleIntegerProperty(0);

    ObservableList<String> trackingNowList;

    @FXML
    private JFXTextField inputCodeField;

    @FXML
    private JFXComboBox vendorOption;
    private ObservableList<String> vendorList = FXCollections.observableArrayList("Thailand Post","Kerry Express", "DHL");
    private String[] vendorColor = {"#ed1c24","#e96514","#fc0"};

    @FXML
    private JFXButton addToTrack;

    @FXML
    private JFXListView<String> trackingListView;

    @FXML
    private HBox emptyMessage;

    public void clickAdd() {
        trackingNowList.add(inputCodeField.getText());
        numOfTrackingList.set(trackingNowList.size());
        System.out.println(numOfTrackingList.get());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vendorOption.setItems(vendorList);
        vendorOption.setValue(vendorList.get(0));
        addToTrack.setStyle("-fx-background-color:" + vendorColor[0] + ";" );

        addToTrack.setOnAction(event -> { clickAdd(); });

        trackingNowList = FXCollections.observableArrayList();
        trackingListView.setItems(trackingNowList);

        numOfTrackingList.set(trackingNowList.size());
        numOfTrackingList.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > 0){
                emptyMessage.setVisible(false);
            }
            else{
                emptyMessage.setVisible(true);
            }
        });

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
    }

//    public void fee(ActionEvent actionEvent) {
//        trackingNowList.remove(0);
//        numOfTrackingList.set(trackingNowList.size());
//        System.out.println(trackingNowList.size());
//    }
}
