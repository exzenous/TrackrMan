package com.TrackrMan;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class ParcelListCell extends JFXListCell<Parcel> {

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

    private FXMLLoader cellLoader = new FXMLLoader(getClass().getResource("/fxml/ParcelListCell.fxml"));

    @Override
    protected void updateItem(Parcel item, boolean empty){
        super.updateItem(item, empty);
        cellLoader.setRoot(null);
        cellLoader.setController(this);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            setText(null);
            try {
                setGraphic(cellLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            setupCell(item);
        }
    }

    public void setupCell(Parcel p){
        this.vendorImage.getStyleClass().addAll("parcel",p.getVendorTag());
        this.parcelName.setText(p.getName());
        this.parcelCode.setText(p.getTrackCode());
        this.statusImage.getStyleClass().addAll("status-image",p.getStatus());
    }

}
