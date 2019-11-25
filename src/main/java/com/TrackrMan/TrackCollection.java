package com.TrackrMan;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TrackCollection implements Serializable{

    //List for ListView to Show
    transient ObservableList<Parcel> trackingList;

    // Number of Items
    transient IntegerProperty  numOfTrackingList = new SimpleIntegerProperty(0);

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

    public void saveList(TrackCollection tc) throws IOException {

        File fileToSave = new File("./savedFile.dat");
        FileOutputStream fileOutBytes = new FileOutputStream(fileToSave);
        ObjectOutputStream fileOutData = new ObjectOutputStream(fileOutBytes);
        
        List foo = new ArrayList();
        for (int i = 0 ; i < trackingList.size() ; i++) {
            foo.add(trackingList.get(i));
        }
        fileOutData.writeObject(foo);

        fileOutData.close();fileOutBytes.close();

        System.out.println("Saved");
    }

    public void loadList() throws IOException, ClassNotFoundException {
        File fileToLoad = new File("./savedFile.dat");
        FileInputStream fileInBytes = new FileInputStream(fileToLoad);
        ObjectInputStream fileInData = new ObjectInputStream(fileInBytes);

        List foo = (ArrayList) fileInData.readObject();

        for (int i = 0 ; i < foo.size() ; i++ ) {
            trackingList.add((Parcel) foo.get(i));
        }

        fileInData.close();fileInBytes.close();
        System.out.println("Loaded");

    }


}
