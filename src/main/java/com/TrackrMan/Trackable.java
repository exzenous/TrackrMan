package com.TrackrMan;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface Trackable {
    Boolean trackThis();
    void turnOffSSLCheck() throws NoSuchAlgorithmException, KeyManagementException;
}
