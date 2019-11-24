package com.TrackrMan;

public class KerryParcel extends Parcel {

    public KerryParcel(String name, String trackCode) {
        super(name, trackCode);
        setVendorTag("kerry");
    }

    public void trackThis(){
        setStatus("deposit");
    }

}
