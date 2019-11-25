package com.TrackrMan;

public class StatusParcel {

    private String dateStatus;
    private String status;
    private String location;

    public StatusParcel(String dateStatus, String status, String location) {
        this.dateStatus = dateStatus;
        this.status = status;
        this.location = location;
    }

    public String getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(String dateStatus) {
        this.dateStatus = dateStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
