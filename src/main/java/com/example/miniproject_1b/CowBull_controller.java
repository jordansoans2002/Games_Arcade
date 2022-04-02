package com.example.miniproject_1b;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CowBull_controller {

    static Scene gameScene;
    static CowBull_multiplayer[] ob;
    static TextField enterGuesses;
    static HBox multiplayer;

    static void startGame() {
        CowBull_controller.gameLayout();
        gameScene.getStylesheets().add(Objects.requireNonNull(CowBull_settings.class.getResource("CowBull.css")).toExternalForm());
        Stage window=new Stage();
        window.setScene(gameScene);
        window.show();
    }
    static void gameLayout(){
        VBox layout=new VBox();
        //layout.setPrefSize(400,320);
        layout.setSpacing(10);
        layout.setPadding(new Insets(20,10,20,10));
        layout.setAlignment(Pos.CENTER);

        HBox group=new HBox(10);
        //group.setPrefSize(400,320);
        group.setAlignment(Pos.CENTER);

        enterGuesses=new TextField();
        enterGuesses.setOnKeyTyped(e->{submit();});
        enterGuesses.setPrefColumnCount(6);
        enterGuesses.setPromptText("guess");

        Label cows=new Label("cows");
        cows.setTextFill(Color.GREEN);
        Label bulls=new Label("bulls");
        bulls.setTextFill(Color.RED);
        group.getChildren().addAll(enterGuesses,cows,bulls);

        multiplayer=new HBox(20);
        multiplayer.setAlignment(Pos.CENTER);
        ob=new CowBull_multiplayer[CowBull_settings.noOfPlayers];
        for(int i=CowBull_settings.noOfPlayers-1;i>=0;i--) {
            ob[i] = new CowBull_multiplayer();
            ob[i].player = i;
            ob[i].playerLayout();
            //ob[i].cowBull("test");
        }

        /*Label toPlay=new Label("Player 1 to play");
        toPlay.setFont(Font.font(15));*/
        layout.getChildren().addAll(group,multiplayer);

        gameScene=new Scene(layout);
    }


    static void submit(){
        String guessedWord=enterGuesses.getText().toUpperCase();

        enterGuesses.setOnKeyPressed(e->{
            String clr="RED";
            if(e.getCode()==KeyCode.ENTER) {
                enterGuesses.clear();
                if (isValidWord(guessedWord)) {
                    //gameplay(guessedWord);
                    clr = "GREEN";
                } else
                    clr = "RED";
                enterGuesses.setBorder(new Border(new BorderStroke(Color.valueOf(clr), BorderStrokeStyle.SOLID, new CornerRadii(4), BorderStroke.MEDIUM)));
            }
            if(clr.equals("GREEN")) {
                gameplay(guessedWord);
            }
        });
    }
    static boolean isValidWord(String guessedWord){
       /* if(!guessedWord.contains("[A-Z]"))
            return false;*/
        if(guessedWord.length() != CowBull_settings.wordLength)
            return false;

        for(int i=0;i<guessedWord.length();i++){
            char c=guessedWord.charAt(i);
            if(!Character.isLetter(c))
                return false;

            for(int j=i+1;j<guessedWord.length();j++){
                if(c==guessedWord.charAt(j))
                    return false;
            }
        }
        return true;
    }

    static void getPoints(){
        for(int i=0;i<CowBull_settings.noOfPlayers;i++) {
            System.out.println((ob[i].player+1)+" has "+ ob[i].points);
        }
    }

    static int chance=0,dir=1;
    static void gameplay(String guessedWord){
        ob[chance].cowBull(guessedWord);
        if(guessedWord.equals(CowBull_settings.target)) {
            getPoints();
            enterGuesses.setEditable(false);
        }

        try{
            if(chance == 0 && CowBull_settings.noOfPlayers>1){
                multiplayer_server.send(guessedWord);
                System.out.println("done sending");
                //enterGuesses.clear();
            }
            chance += dir;
            nextTurn();
            if(chance!=0) {
                System.out.println("waiting for msg");
                multiplayer_server.send(chance+"");
                String inMsg = multiplayer_server.received().toUpperCase();
                gameplay(inMsg);
            }

        } catch (IOException e){
            //todo
        }
        /*chance += dir;
        nextTurn();*/
        enterGuesses.setPromptText("player "+(chance+1));
    }
    static void nextTurn(){
        if(chance==CowBull_settings.noOfPlayers){
            dir=-1;
            chance=CowBull_settings.noOfPlayers-1;
        }
        if(chance==-1){
            dir=1;
            chance=0;
        }
    }
}
