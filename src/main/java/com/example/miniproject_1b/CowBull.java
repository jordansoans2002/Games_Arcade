package com.example.miniproject_1b;

/*import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;*/

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CowBull {
    int difficulty=0,wordLength=4,points=0,noOfGuesses=0;
    int maxBulls=0,maxCows=0;
    String target=setWord();
    List<String> guesses=new ArrayList<>();
    //HBox guess;

    /*CowBull(int wordLength,int difficulty){
        this.wordLength=wordLength;
        this.difficulty=difficulty;
        target=setWord();
    }*/

    String setWord(){
        String target="SNOW";
        if(wordLength==3) target="NOW";
        if(wordLength==4) target="SNOW";
        return target;
    }

    @FXML
    public GridPane allGuesses;
    @FXML
    public TextField enterGuesses;
    @FXML
    void submit(){
        String guessedWord=enterGuesses.getText().toUpperCase();
        enterGuesses.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER) {
                enterGuesses.clear();
                if(isValidWord(guessedWord)){
                    noOfGuesses++;
                    guesses.add(guessedWord);
                    isOver(guessedWord);
                    cowBull(guessedWord);
                }
            }
        });
    }
    void isOver(String guessedWord){
        if(guessedWord.equals(target))
            enterGuesses.setEditable(false);
    }
    boolean isValidWord(String guessedWord){
       /* if(!guessedWord.contains("[A-Z]"))
            return false;*/
        if(guessedWord.length() != wordLength)
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
        guess.setAlignment(Pos.CENTER_LEFT);
        Hyperlink test=new Hyperlink("https://www.google.com/search?q="+guessedWord+"+meaning",guess);

        int cows = 0, bulls = 0;
        String clr = "white";
        for (int i = 0; i < wordLength; i++) {
            if (guessedWord.charAt(i) == target.charAt(i)) {
                bulls++;
                clr = "RED";
            } else {
                clr = "WHITE";
                for (int j = 0; j < wordLength; j++) {
                    if (guessedWord.charAt(i) == target.charAt(j)) {
                        cows++;
                        clr = "GREEN";
                        break;
                    }
                }
            }
            addLetter(guess,String.valueOf(guessedWord.charAt(i)), clr);
        }
        addGuess(guess,cows,bulls);
    }

    void addGuess(HBox guess,int cows,int bulls)
    {
        Label cow=new Label(cows+"");
        cow.setTextFill(Color.GREEN);
        Label bull=new Label(bulls+"");
        bull.setTextFill(Color.RED);

        GridPane.setConstraints(guess,0,noOfGuesses);
        allGuesses.getChildren().add(guess);
        if(difficulty!=0) {
            GridPane.setConstraints(cow, 1, noOfGuesses);
            GridPane.setConstraints(bull, 2, noOfGuesses);
            allGuesses.getChildren().addAll(cow,bull);
        }
        guess.setOnMousePressed(e->checkWord(GridPane.getRowIndex(guess)));
        calcPoints(cows,bulls);
    }
    void calcPoints(int cows,int bulls){
        maxCows=Math.max(cows,maxCows);
        maxBulls=Math.max(bulls,maxBulls);
        points=maxBulls*3+maxCows;
        System.out.println(points);
    }
    void addLetter(HBox guess,String c,String clr){
        Label letter=new Label(c);
        letter.setPrefWidth(30);
        letter.setPrefHeight(30);
        letter.setAlignment(Pos.CENTER);
        if(difficulty!=0) clr="WHITE";
        BackgroundFill bgfill = new BackgroundFill(Color.valueOf(clr), new CornerRadii(4), new Insets(0));
        letter.setBackground(new Background(bgfill));
        guess.getChildren().add(letter);
    }

    void checkWord(int row){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Hanniel\\Desktop\\Jordan(desktop)\\engg software\\Chromedrivers");
        String word=guesses.get(row-1);
        System.out.println(guesses.get(row-1));
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + word + "+meaning"));
        }catch (Exception e){
        }
    }

}