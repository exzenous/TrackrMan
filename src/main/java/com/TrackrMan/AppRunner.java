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

    // Attribute Block START
    JFXButton trackButton, knowledgeButton, aboutButton, exitButton;

    BorderPane wholeWindowView, sideButtonsView;

    TrackCollection trackCollection;

    // Attribute Block END

    // JavaFX moved all main Processes to start Method
    public static void main(String[] args) {
        launch(args);
    }

    // Start Method to Initialize Program; Init Window
    @Override
    public void start(Stage mainWindow) throws Exception {
        // Setup Window
        mainWindow.setTitle("TrackMan");
        mainWindow.setMinWidth(1200);
        mainWindow.setMinHeight(800);

        // Load Views
        AnchorPane trackView = FXMLLoader.load(getClass().getResource("/fxml/TrackPane.fxml"));
        AnchorPane knowledgeView = FXMLLoader.load(getClass().getResource("/fxml/KnowledgePane.fxml"));
        AnchorPane aboutView = FXMLLoader.load(getClass().getResource("/fxml/AboutPane.fxml"));

        // Load new List Object into TrackView

        // Init Each Part of Components
        wholeWindowView = new BorderPane();
        sideButtonsView = new BorderPane();

        // Init Whole Screen
        Scene loadedScreen = new Scene(wholeWindowView,1200,800);

        // Init Sidebar Button Start Block
        // Track Button
        Image imageSearch = new Image(getClass().getResourceAsStream("/img/track.png"));
        ImageView imageSearchView = new ImageView(imageSearch);
        trackButton = new JFXButton("Track", imageSearchView);
        trackButton.setContentDisplay(ContentDisplay.TOP);
        trackButton.setOnAction(e -> {
            wholeWindowView.setCenter(trackView);
        });

        // Knowledge Button
        Image imageKnowledge = new Image(getClass().getResourceAsStream("/img/knowledge.png"));
        ImageView imageKnowledgeView = new ImageView(imageKnowledge);
        knowledgeButton = new JFXButton("Knowledge",imageKnowledgeView);
        knowledgeButton.setContentDisplay(ContentDisplay.TOP);
        knowledgeButton.setOnAction(e -> {
            wholeWindowView.setCenter(knowledgeView);
        });

        // About Button
        Image imageAbout = new Image(getClass().getResourceAsStream("/img/about.png"));
        ImageView imageAboutView = new ImageView(imageAbout);
        aboutButton = new JFXButton("About", imageAboutView);
        aboutButton.setContentDisplay(ContentDisplay.TOP);
        aboutButton.setOnAction(e -> {
            wholeWindowView.setCenter(aboutView);
        });

        // Exit Button
        Image imageExit = new Image(getClass().getResourceAsStream("/img/exit.png"));
        ImageView imageExitView = new ImageView(imageExit);
        exitButton = new JFXButton("Exit",imageExitView);
        exitButton.setContentDisplay(ContentDisplay.TOP);
        exitButton.setOnAction(e -> {
            mainWindow.close();
        });
        // Init Sidebar Button End Block

        // Group Buttons
        VBox upperGroupButton = new VBox(trackButton, knowledgeButton, aboutButton);

        sideButtonsView.setCenter(upperGroupButton);
        sideButtonsView.setBottom(exitButton);
        wholeWindowView.setLeft(sideButtonsView);

        sideButtonsView.setPrefWidth(150);
        sideButtonsView.setStyle("-fx-background-color: #005b9f");
        sideButtonsView.getStylesheets().add(getClass().getResource("/css/AppStyle.css").toExternalForm());

        // Finalize Window
        wholeWindowView.setCenter(trackView);
        mainWindow.setScene(loadedScreen);
        mainWindow.show();

    }
}
