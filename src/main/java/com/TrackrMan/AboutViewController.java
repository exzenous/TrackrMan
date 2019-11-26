package com.TrackrMan;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {

    public String version = "release1.0.1";
    public String buildNumber = "0132";
    public Label versionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionLabel.setText("Version " + version + " Build " + buildNumber);
    }
}
