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

    JFXButton trackButton, historyButton, aboutButton, exitButton;

    BorderPane windowView;
    GridPane sideButtonsPane;

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

        sideButtonsPane = new GridPane();

        Scene loadedScreen = new Scene(windowView,1200,900);

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
        exitButton = new JFXButton("Exit");
        exitButton.setOnAction(e -> {
            mainWindow.close();
        });


        final int numRows = 10 ;
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            sideButtonsPane.getRowConstraints().add(rowConst);
        }

        GridPane.setConstraints(trackButton,0,0);
        GridPane.setConstraints(historyButton,0,1);
        GridPane.setConstraints(aboutButton,0,2);
        GridPane.setConstraints(exitButton,0,9);
        sideButtonsPane.getChildren().addAll(trackButton, historyButton, aboutButton, exitButton);

        sideButtonsPane.setVgap(0);
        sideButtonsPane.setPrefWidth(150);

        sideButtonsPane.setStyle("-fx-background-color: #005b9f");

        windowView.setLeft(sideButtonsPane);
        windowView.setCenter(trackView);

        loadedScreen.getStylesheets().add(getClass().getResource("AppStyle.css").toExternalForm());

        mainWindow.setScene(loadedScreen);

        mainWindow.show();

    }
}
