package com.TrackrMan;

public class KerryParcel extends Parcel {

    public KerryParcel(String name, String trackCode) {
        super(name, trackCode);
        setVendorTag("kerry");
    }

    public Boolean trackThis(){
        setStatus("deposit");
        return null;
    }

}
