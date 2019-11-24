package com.TrackrMan;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TrackCollection {

    /*
    *
    *
    *
    * */

    //List for ListView to Show
    private ObservableList<Parcel> trackingList;

    // Number of Items
    private IntegerProperty numOfTrackingList = new SimpleIntegerProperty(0);

    public TrackCollection() {

        trackingList = FXCollections.observableArrayList();

    }

    public ObservableList<Parcel> getTrackingList() {
        return trackingList;
    }

    public IntegerProperty getNumOfTrackingList() {
        return numOfTrackingList;
    }

    public IntegerProperty numOfTrackingListProperty() {
        return numOfTrackingList;
    }

    public void saveList(ObservableList<Parcel> list){

    }

    public void loadList(){

    }

}
