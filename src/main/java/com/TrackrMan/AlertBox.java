package com.TrackrMan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    static Boolean answer;

    public static String AskForNaming(String title, String message){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);

        Label msg = new Label(message);

        JFXTextField nameParcel = new JFXTextField();

        JFXButton yesButton, noButton;

        yesButton = new JFXButton("Yes");
        yesButton.setOnAction(e -> {
            if ((nameParcel.getText().trim()).equals("")) {
                AlertBox.ErrorMsgNoReply("Error!","You haven't enter any name.");
            }
            else {
                answer = true;
                window.close();
            }
        });
        noButton = new JFXButton("No");
        noButton.setOnAction(e -> {answer = false;window.close();});
        HBox groupButton = new HBox(yesButton,noButton);
        groupButton.setAlignment(Pos.CENTER);

        VBox groupBox = new VBox(20);
        groupBox.getChildren().addAll(msg,nameParcel,groupButton);

        groupBox.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(groupBox);

        window.setScene(scene);window.showAndWait();

        if(!answer){
            return "Untitled Parcel";
        }
        else{
            return nameParcel.getText();
        }

    }

    public static void ErrorMsgNoReply(String title, String message){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);

        Label msg = new Label(message);
        JFXButton okButton;

        okButton = new JFXButton("OK");
        okButton.setOnAction(e -> {window.close(); });
        HBox groupButton = new HBox(okButton);
        groupButton.setAlignment(Pos.CENTER);

        VBox groupBox = new VBox(20);
        groupBox.getChildren().addAll(msg,groupButton);

        groupBox.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(groupBox);

        window.setScene(scene);
        window.show();

    }

}
