package com.TrackrMan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class AlertBox {

    static Boolean answer;

    public static String AskForNaming(String title, String message){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.setOnCloseRequest(event -> {
            event.consume();
        });

        Label msg = new Label(message);

        JFXTextField nameParcel = new JFXTextField();
        nameParcel.setPromptText("Enter a Name");

        JFXButton yesButton, noButton;

        yesButton = new JFXButton("Yes");
        yesButton.setOnAction(e -> {
            if ((nameParcel.getText().trim()).equals("")) {
                AlertBox.ErrorMsgNoReply("Error!","You haven't entered any name.");
            }
            else {
                answer = true;
                window.close();
            }
        });
        yesButton.getStyleClass().add("button-yes");

        noButton = new JFXButton("No");
        noButton.setOnAction(e -> {answer = false;window.close();});
        noButton.getStyleClass().add("button-no");

        BorderPane boxMessage = new BorderPane();

        VBox groupPrompt = new VBox(20);
        groupPrompt.getChildren().addAll(msg,nameParcel);
        ImageView alertImage = new ImageView(new Image("/img/alert-info.png"));
        HBox foo = new HBox(alertImage);
        foo.setAlignment(Pos.CENTER);
        foo.setPadding(new Insets(0,20,0,0));
        boxMessage.setCenter(groupPrompt);
        boxMessage.setLeft(foo);

        //Group Button
        HBox groupButton = new HBox(yesButton,noButton);
        groupButton.setAlignment(Pos.CENTER);
        groupButton.getStylesheets().add("/css/ButtonAlert.css");
        groupButton.setSpacing(20);

        //Whole Scene
        VBox groupBox = new VBox(20);
        groupBox.setPadding(new Insets(20,20,20,20));
        groupBox.getChildren().addAll(boxMessage,groupButton);

        window.setScene(new Scene(groupBox));
        window.showAndWait();

        if(!answer){
            return "Untitled Parcel";
        }
        else{
            return nameParcel.getText();
        }

    }

    public static Boolean AskForConfirm(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.setOnCloseRequest(Event::consume);

        HBox imageBox = new HBox(new ImageView(new Image("/img/alert-warn.png")));
        imageBox.setAlignment(Pos.CENTER);
        Label msg = new Label(message);
        JFXButton yesButton, noButton;

        yesButton = new JFXButton("Yes");
        yesButton.setOnAction(e -> {
            answer = true; window.close();
        });
        yesButton.getStyleClass().add("button-yes");

        noButton = new JFXButton("No");
        noButton.setOnAction(e -> {
            answer = false; window.close();
        });
        noButton.getStyleClass().add("button-no");

        HBox groupButton = new HBox(yesButton,noButton);
        groupButton.setSpacing(20);
        groupButton.setAlignment(Pos.CENTER);
        groupButton.getStylesheets().add("/css/ButtonAlert.css");
        HBox messageBox = new HBox(imageBox,msg);
        messageBox.setSpacing(20);
        messageBox.setAlignment(Pos.CENTER);
        VBox groupBox = new VBox(20);
        groupBox.getChildren().addAll(messageBox,groupButton);
        groupBox.setPadding(new Insets(20,20,20,20));

        window.setScene(new Scene(groupBox));

        window.showAndWait();

        return answer;

    }

    public static void ErrorMsgNoReply(String title, String message){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);

        HBox imageBox = new HBox(new ImageView(new Image("/img/alert-warn.png")));
        imageBox.setAlignment(Pos.CENTER);
        Label msg = new Label(message);
        JFXButton okButton;

        okButton = new JFXButton("OK");
        okButton.setOnAction(e -> {window.close(); });
        okButton.getStyleClass().add("button-ok");
        HBox groupButton = new HBox(okButton);
        groupButton.setAlignment(Pos.CENTER);
        groupButton.getStylesheets().add("/css/ButtonAlert.css");

        HBox groupMsg = new HBox(imageBox,msg);
        groupMsg.setAlignment(Pos.CENTER);
        groupMsg.setSpacing(20);

        VBox groupBox = new VBox(20);
        groupBox.getChildren().addAll(groupMsg,groupButton);

        groupBox.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(groupBox);

        window.setScene(scene);
        window.show();
        Toolkit.getDefaultToolkit().beep();

    }

}
