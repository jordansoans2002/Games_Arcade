package com.example.miniproject_1b;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CowBull {
    int player;

    int maxBulls=0,maxCows=0,points=0,noOfGuesses=0;
    List<String> guesses=new ArrayList<>();

    /*@FXML
    AnchorPane gamePane;
    AnchorPane main(){
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.getChildren().add(gamePane);
        return anchorPane;
    }*/

    @FXML
    public GridPane allGuesses;
    @FXML
    public TextField enterGuesses;
    @FXML
    void submit(){
        String guessedWord=enterGuesses.getText().toUpperCase();
        enterGuesses.setOnKeyPressed(e->{
            String clr;
            if(e.getCode()== KeyCode.ENTER) {
                enterGuesses.clear();
                if(isValidWord(guessedWord)){
                    noOfGuesses++;
                    guesses.add(guessedWord);
                    isOver(guessedWord);
                    cowBull(guessedWord);
                    clr="GREEN";
                }
                else
                    clr="RED";

                enterGuesses.setBorder(new Border(new BorderStroke(Color.valueOf(clr),BorderStrokeStyle.SOLID,new CornerRadii(4),BorderStroke.MEDIUM)));
            }
        });
    }
    void isOver(String guessedWord){
        if(guessedWord.equals(CowBull_settings.target)) {
            enterGuesses.setEditable(false);
            CowBull_settings.getPoints();
        }
    }
    boolean isValidWord(String guessedWord){
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

    void cowBull(String guessedWord) {
        HBox guess = new HBox();
        guess.setId("letters");
        guess.setPrefWidth(90);
        guess.setSpacing(2);
        guess.setAlignment(Pos.CENTER_LEFT);

        int cows = 0, bulls = 0;
        String clr = "white";
        for (int i = 0; i < CowBull_settings.wordLength; i++) {
            if (guessedWord.charAt(i) == CowBull_settings.target.charAt(i)) {
                bulls++;
                if(CowBull_settings.difficulty==2)
                    clr="GREEN";
                if(CowBull_settings.difficulty==1)
                    clr = "RED";
            } else {
                clr = "WHITE";
                for (int j = 0; j < CowBull_settings.wordLength; j++) {
                    if (guessedWord.charAt(i) == CowBull_settings.target.charAt(j)) {
                        cows++;
                        clr = "GREEN";
                        break;
                    }
                }
            }
            addLetter(guess,String.valueOf(guessedWord.charAt(i)), clr);
        }
        addGuess(guess,cows,bulls);
        calcPoints(cows,bulls);
    }
    void addLetter(HBox guess,String c,String clr){
        Label letter=new Label(c);
        letter.setPrefWidth(30);
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
        cow.setTextFill(Color.GREEN);
        Label bull=new Label(bulls+"");
        bull.setTextFill(Color.RED);

        GridPane.setConstraints(guess,0,noOfGuesses);
        allGuesses.getChildren().add(guess);
        if(CowBull_settings.difficulty !=1) {
            GridPane.setConstraints(cow, 1, noOfGuesses);
            GridPane.setConstraints(bull, 2, noOfGuesses);
            allGuesses.getChildren().addAll(cow,bull);
        }
        guess.setOnMousePressed(e->checkWord(GridPane.getRowIndex(guess)));
    }
    void calcPoints(int cows,int bulls){
        maxCows=Math.max(cows,maxCows);
        maxBulls=Math.max(bulls,maxBulls);
        points=maxBulls*3+maxCows;
        System.out.println(points);
    }


    void checkWord(int row){
        String word=guesses.get(row-1);
        System.out.println(guesses.get(row-1));
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + word + "+meaning"));
        }catch (Exception e){
        }
    }

}