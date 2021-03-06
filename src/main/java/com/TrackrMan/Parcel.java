package com.TrackrMan;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.net.ssl.*;
import java.io.Serializable;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public abstract class Parcel implements Trackable, Serializable {

    // Attibutes
    private String name;
    private String trackCode;
    private String status;
    private String realStatus;
    private String vendorTag;
    private String jsonString;


    public Parcel(String name, String trackCode) {
        setName(name);
        setTrackCode(trackCode);
    }

    // Getters and Setters
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

    public String getRealStatus() {
        return realStatus;
    }
    public void setRealStatus(String realStatus) {
        this.realStatus = realStatus;
    }

    public String getVendorTag() { return vendorTag; }
    public void setVendorTag(String vendorTag) { this.vendorTag = vendorTag; }

    public String getJsonString() {
        return jsonString;
    }
    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public void turnOffSSLCheck() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
}
