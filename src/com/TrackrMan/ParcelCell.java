package com.TrackrMan;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class ParcelCell extends JFXListCell<Parcel> {

    @FXML
    private AnchorPane statusImage;

    @FXML
    private AnchorPane vendorImage;

    @FXML
    private Label etaText;

    @FXML
    private Label parcelName;

    @FXML
    private Label parcelCode;

    @Override
    protected void updateItem(Parcel item, boolean empty){
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            System.out.println(this.getIndex());
        }
    }

}
