package com.example.miniproject_1b;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;

public class CowBull_controller {
    static int playerNo=0;

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
        window.setOnCloseRequest(e->{
            if(CowBull_settings.multiPC) {
                if (playerNo == 0)
                    multithread_server.closeChat();
                else
                    multithread_client.closeChat();
            }
        });

        if(CowBull_settings.noOfPlayers>1 && CowBull_settings.multiPC) {
            //multiplayer_server.main();
            if(playerNo == 0)
                new multithread_server().start();
            else
                new multithread_client().start();
        }
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
        enterGuesses.setEditable(playerNo == 0);
        enterGuesses.setOnKeyTyped(e->submit());
        enterGuesses.setPrefColumnCount(6);
        enterGuesses.setPromptText("guess");

        Label cows=new Label("cows");
        cows.setTextFill(Color.ORANGE);
        Label bulls=new Label("bulls");
        bulls.setTextFill(Color.GREEN);
        group.getChildren().addAll(enterGuesses,cows,bulls);

        multiplayer=new HBox(20);
        multiplayer.setAlignment(Pos.CENTER);
        ob=new CowBull_multiplayer[CowBull_settings.noOfPlayers];
        for(int i=0;i<CowBull_settings.noOfPlayers;i++) {
            ob[i] = new CowBull_multiplayer();
            ob[i].player = i;
            ob[i].playerLayout();
        }

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
        guessedWord=guessedWord.toUpperCase();
        ob[chance].cowBull(guessedWord);
        if(guessedWord.equals(CowBull_settings.target)) {
            getPoints();
            enterGuesses.setEditable(false);
        }


        if(chance == playerNo && CowBull_settings.noOfPlayers>1 && CowBull_settings.multiPC){
            //multiplayer_server.send(guessedWord);
            if(playerNo == 0)
                multithread_server.send(guessedWord);
            else
                multithread_client.send(guessedWord);
        }
        chance += dir;
        nextTurn();
        if(chance !=0 && playerNo == 0 && CowBull_settings.multiPC) {
            /*multiplayer_server.send(chance+"");
               String inMsg = multiplayer_server.receive().toUpperCase();
               gameplay(inMsg);*/

            multithread_server.send(String.valueOf(chance));
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
        if(playerNo != 0) //server can enter guess for any player
            enterGuesses.setEditable(chance == playerNo);
    }
    static void getGuess(String guess){
        Platform.runLater(() -> {
            if(guess.length()<3) {
                int toPlay=Integer.parseInt(guess);
                if(toPlay==playerNo)
                    enterGuesses.setEditable(true);
            }
            gameplay(guess);
        });
    }
}
