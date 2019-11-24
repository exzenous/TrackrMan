package com.TrackrMan;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TrackCollection {

    //List for ListView to Show
    static ObservableList<Parcel> trackingList;

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

    public static void saveList(){


        System.out.println("Saved");
    }

    public static void loadList(){

    }

}
