package com.TrackrMan;

public class DHLParcel extends Parcel {

    public DHLParcel(String name, String trackCode) {
        super(name, trackCode);
        setVendorTag("dhl");
    }

    public void trackThis(){
        setStatus("deposit");
    }

}
