package com.TrackrMan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppRunner extends Application {

    Button trackButton, aboutButton;
    BorderPane windowView;
    AnchorPane sideButtonsPane;
    AnchorPane workingPane;

    public class ClickAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        mainWindow.setTitle("TrackMan");

        AnchorPane trackView = FXMLLoader.load(getClass().getResource("TrackPane.fxml"));
        AnchorPane aboutView = FXMLLoader.load(getClass().getResource("AboutPane.fxml"));

        windowView = new BorderPane();
        sideButtonsPane = new AnchorPane();
        workingPane = new AnchorPane();

        trackButton = new Button("Track");
        trackButton.setOnAction(e -> {
            windowView.setCenter(trackView);
        });
        aboutButton = new Button("About");
        aboutButton.setOnAction(e -> {
            windowView.setCenter(aboutView);
        });
        VBox groupedButtons = new VBox(trackButton, aboutButton);

        sideButtonsPane.setPrefWidth(150);
        sideButtonsPane.getChildren().add(groupedButtons);

        windowView.setLeft(sideButtonsPane);
        windowView.setCenter(workingPane);

        workingPane.getChildren().add(trackView);

        Scene loadedScreen = new Scene(windowView,600,400);

        mainWindow.setScene(loadedScreen);

        mainWindow.show();

    }
}
