package com.TrackrMan;

public class ThaiPostParcel extends Parcel {

    public ThaiPostParcel(String name, String trackCode) {
        super(name, trackCode);
        setVendorTag("thailandpost");
    }

}
