package com.TrackrMan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class KnowledgeViewController implements Initializable {

    @FXML
    private Accordion accord;

    @FXML
    private TitledPane firstPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accord.setExpandedPane(firstPane);
    }
}
