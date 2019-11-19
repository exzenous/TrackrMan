package com.TrackrMan;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppRunner extends Application {

    JFXButton trackButton, historyButton, aboutButton;

    BorderPane windowView;
    AnchorPane sideButtonsPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        mainWindow.setTitle("TrackMan");
        mainWindow.setMinWidth(800);
        mainWindow.setMinHeight(600);

        AnchorPane trackView = FXMLLoader.load(getClass().getResource("TrackPane.fxml"));
        AnchorPane aboutView = FXMLLoader.load(getClass().getResource("AboutPane.fxml"));

        windowView = new BorderPane();

        sideButtonsPane = new AnchorPane();

        Scene loadedScreen = new Scene(windowView,800,600);

        trackButton = new JFXButton("Track");
        trackButton.setOnAction(e -> {
            windowView.setCenter(trackView);
        });
        historyButton = new JFXButton("History");
        historyButton.setOnAction(e -> {
            System.out.println("AppRunner.start");
        });
        aboutButton = new JFXButton("About");
        aboutButton.setOnAction(e -> {
            windowView.setCenter(aboutView);
        });

        VBox groupedButtons = new VBox(trackButton, historyButton, aboutButton);

        sideButtonsPane.setPrefWidth(150);
        sideButtonsPane.getChildren().add(groupedButtons);

        sideButtonsPane.setStyle("-fx-background-color:#AAAAAA");

        windowView.setLeft(sideButtonsPane);
        windowView.setCenter(trackView);

        loadedScreen.getStylesheets().add(getClass().getResource("AppStyle.css").toExternalForm());

        mainWindow.setScene(loadedScreen);

        mainWindow.show();

    }
}
