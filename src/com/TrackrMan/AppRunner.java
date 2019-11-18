package com.TrackrMan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppRunner extends Application {

    Button button1,button2,button3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        mainWindow.setTitle("TrackMan");

        GridPane gridPane = new GridPane();
        Scene firstScene = new Scene(gridPane);

        button1 = new Button("First");
        button2 = new Button("Second");
        button3 = new Button("Third");
        gridPane.add(button1,1,1);
        gridPane.add(button2,1,2);
        gridPane.add(button3,1,3);

        mainWindow.setScene(firstScene);
        mainWindow.sizeToScene();
        mainWindow.show();

    }
}
