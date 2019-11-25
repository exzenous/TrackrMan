package com.TrackrMan;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {

    public String version = "beta0.3";
    public String buildNumber = "0109";
    public Label versionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionLabel.setText("Version " + version + " Build " + buildNumber);
    }
}
