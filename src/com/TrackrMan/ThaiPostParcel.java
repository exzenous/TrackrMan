package com.TrackrMan;

public class ThaiPostParcel extends Parcel {

    private String vendor = "Thailand Post";

    public ThaiPostParcel(String name, String trackCode) {
        super(name, trackCode);
    }

    public String getVendor() {
        return vendor;
    }

}
