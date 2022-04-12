package com.example.miniproject_1b;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CowBull_multiplayer extends CowBull_controller{
    int player;

    @FXML
    ScrollPane gameScroller;
    @FXML
    GridPane allGuesses;

    int noOfGuesses=0,points=0,maxCows=0,maxBulls=0;
    List<String> guesses=new ArrayList<>();

    void playerLayout(){
        gameScroller=new ScrollPane();
        gameScroller.setPrefHeight(300);
        gameScroller.setPrefWidth(250);
        gameScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameScroller.setFitToWidth(true);

        allGuesses=new GridPane();
        allGuesses.setPrefHeight(300);
        allGuesses.setPrefWidth(250);
        allGuesses.setAlignment(Pos.TOP_CENTER);
        allGuesses.setVgap(7);
        allGuesses.setHgap(40);
        allGuesses.setPadding(new Insets(3,0,0,0));

        gameScroller.setContent(allGuesses);
        CowBull_controller.multiplayer.getChildren().add(gameScroller);
    }

    void cowBull(String guessedWord) {
        HBox guess = new HBox();
        guess.setId("letters");
        guess.setPrefWidth(120);
        guess.setSpacing(1);
        guess.setAlignment(Pos.CENTER_LEFT);

        noOfGuesses++;
        guesses.add(guessedWord);

        int cows = 0, bulls = 0;
        String clr = "white";
        for (int i = 0; i < CowBull_settings.wordLength; i++) {
            if (guessedWord.charAt(i) == CowBull_settings.target.charAt(i)) {
                bulls++;
                if(CowBull_settings.difficulty==2)
                    clr="ORANGE";
                if(CowBull_settings.difficulty==1)
                    clr = "GREEN";
            } else {
                clr = "WHITE";
                for (int j = 0; j < CowBull_settings.wordLength; j++) {
                    if (guessedWord.charAt(i) == CowBull_settings.target.charAt(j)) {
                        cows++;
                        clr = "ORANGE";
                        break;
                    }
                }
            }
            addLetter(guess,String.valueOf(guessedWord.charAt(i)), clr);
        }
        //System.out.println("player "+player+" guess "+guessedWord+" "+cows+" "+bulls);
        addGuess(guess,cows,bulls);
        calcPoints(cows,bulls);
    }
    void addLetter(HBox guess,String c,String clr){
        Label letter=new Label(c);
        letter.setPrefWidth(20);
        letter.setPrefHeight(30);
        letter.setAlignment(Pos.CENTER);
        if(CowBull_settings.difficulty ==3) clr="WHITE";
        BackgroundFill bgfill = new BackgroundFill(Color.valueOf(clr), new CornerRadii(4), new Insets(0));
        letter.setBackground(new Background(bgfill));
        guess.getChildren().add(letter);
    }
    void addGuess(HBox guess,int cows,int bulls)
    {
        Label cow=new Label(cows+"");
        cow.setTextFill(Color.ORANGE);
        Label bull=new Label(bulls+"");
        bull.setTextFill(Color.GREEN);

        GridPane.setConstraints(guess,0,noOfGuesses);
        allGuesses.getChildren().add(guess);
        if(CowBull_settings.difficulty !=1) {
            GridPane.setConstraints(cow, 1, noOfGuesses);
            GridPane.setConstraints(bull, 2, noOfGuesses);
            allGuesses.getChildren().addAll(cow,bull);
        }
        gameScroller.setVvalue(allGuesses.getHeight());
        guess.setOnMousePressed(e->checkWord(GridPane.getRowIndex(guess)));
    }
    void calcPoints(int cows,int bulls){
        maxCows=Math.max(cows,maxCows);
        maxBulls=Math.max(bulls,maxBulls);
        points=maxBulls*3+maxCows;
    }


    void checkWord(int row){
        String word=guesses.get(row-1);
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + word + "+meaning"));
        }catch (Exception e){
            //todo
        }
    }
}
