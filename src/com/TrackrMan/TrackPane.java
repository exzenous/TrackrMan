package com.TrackrMan;

import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackPane implements Initializable {

    ObservableList<String> trackingNowList;

    @FXML
    private Label jumboTitle;

    @FXML
    private Label subTitle;

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

    public void clickAdd() {
        trackingListView.getItems().add(inputCodeField.getText() + " " + vendorOption.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Starto!");

        vendorOption.setItems(vendorList);
        vendorOption.setValue(vendorList.get(0));
        addToTrack.setStyle("-fx-background-color:" + vendorColor[0] + ";" );

        addToTrack.setOnAction(event -> { clickAdd(); });

        trackingNowList = FXCollections.observableArrayList();
        trackingListView.setItems(trackingNowList);

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
}
