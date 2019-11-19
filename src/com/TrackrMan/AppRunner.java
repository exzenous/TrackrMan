package com.TrackrMan;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppRunner extends Application {

    JFXButton trackButton, historyButton, aboutButton, exitButton;

    BorderPane windowView;
    BorderPane sideButtonsPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        mainWindow.setTitle("TrackMan");
        mainWindow.setMinWidth(800);
        mainWindow.setMinHeight(650);

        AnchorPane trackView = FXMLLoader.load(getClass().getResource("TrackPane.fxml"));
        AnchorPane aboutView = FXMLLoader.load(getClass().getResource("AboutPane.fxml"));

        windowView = new BorderPane();

        sideButtonsPane = new BorderPane();

        Scene loadedScreen = new Scene(windowView,800,600);

        Image imageSearch = new Image(getClass().getResourceAsStream("img/icons8-search_filled.png"));
        ImageView imageSearchView = new ImageView(imageSearch);
        trackButton = new JFXButton("Track", imageSearchView);
        trackButton.setContentDisplay(ContentDisplay.TOP);
        trackButton.setOnAction(e -> {
            windowView.setCenter(trackView);
        });
        Image imageHistory = new Image(getClass().getResourceAsStream("img/icons8-checked.png"));
        ImageView imageHistoryView = new ImageView(imageHistory);
        historyButton = new JFXButton("History",imageHistoryView);
        historyButton.setContentDisplay(ContentDisplay.TOP);
        historyButton.setOnAction(e -> {
            System.out.println("AppRunner.start");
        });
        Image imageAbout = new Image(getClass().getResourceAsStream("img/icons8-info.png"));
        ImageView imageAboutView = new ImageView(imageAbout);
        aboutButton = new JFXButton("About", imageAboutView);
        aboutButton.setContentDisplay(ContentDisplay.TOP);
        aboutButton.setOnAction(e -> {
            windowView.setCenter(aboutView);
        });
        Image imageExit = new Image(getClass().getResourceAsStream("img/icons8-exit_sign.png"));
        ImageView imageExitView = new ImageView(imageExit);
        exitButton = new JFXButton("Exit",imageExitView);
        exitButton.setContentDisplay(ContentDisplay.TOP);
        exitButton.setOnAction(e -> {
            mainWindow.close();
        });

        VBox centerBtns = new VBox(trackButton, historyButton, aboutButton);

        sideButtonsPane.setCenter(centerBtns);
        sideButtonsPane.setBottom(exitButton);

        sideButtonsPane.setPrefWidth(150);

        sideButtonsPane.setStyle("-fx-background-color: #005b9f");

        windowView.setLeft(sideButtonsPane);
        windowView.setCenter(trackView);

        loadedScreen.getStylesheets().add(getClass().getResource("AppStyle.css").toExternalForm());

        mainWindow.setScene(loadedScreen);

        mainWindow.show();

    }
}
