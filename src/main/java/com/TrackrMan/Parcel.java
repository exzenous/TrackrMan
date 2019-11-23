package com.TrackrMan;

public abstract class Parcel{

    private String name;
    private String trackCode;
    private String status;
    private String vendorTag;

    public Parcel(String name, String trackCode) {
        this.name = name;
        this.trackCode = trackCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTrackCode() {
        return trackCode;
    }
    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorTag() { return vendorTag; }

    public void setVendorTag(String vendorTag) { this.vendorTag = vendorTag; }

    public void trackThis(){ }
}
