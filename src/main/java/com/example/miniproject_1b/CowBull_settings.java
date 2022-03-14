package com.example.miniproject_1b;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CowBull_settings extends Application {

    public Button easy;
    @FXML
    void setEasy(){
        setDifficulty(1);
    }
    public Button medium;
    @FXML
    void setMedium(){
        setDifficulty(2);
    }
    public Button hard;
    @FXML
    void setHard(){
        setDifficulty(3);
    }
    public Button letters3;
    @FXML
    void setLetters3(){
        setWordLength(3);
    }
    public Button letters4;
    @FXML
    void setLetters4(){
        setWordLength(4);
    }
    public Button letters5;
    @FXML
    void setLetters5(){
        setWordLength(5);
    }
    public Button playButton;

    int noOfPlayers=1,difficulty=2,wordLength=4;

    static void main(String []args){launch();}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(CowBull_settings.class.getResource("CowBull_settings.fxml"));
        Scene settingsScene=new Scene(fxmlLoader.load());
        stage.setScene(settingsScene);
        stage.setTitle("Settings");
        stage.show();
    }
    @FXML
    void changeScene() throws IOException {
        Stage window=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(CowBull_settings.class.getResource("Cow_Bull.fxml"));
        HBox multiplayer=new HBox(fxmlLoader.load());
        multiplayer.setSpacing(10);
        Scene gameScene=new Scene(multiplayer);
        gameScene.getStylesheets().add(Objects.requireNonNull(CowBull_test.class.getResource("CowBull.css")).toExternalForm());
        window.setScene(gameScene);
        window.setTitle("CowBull game");
        window.show();
    }

    void setWordLength(int l){
        wordLength=l;
        BackgroundFill bgRed = new BackgroundFill(Color.valueOf("RED"), new CornerRadii(4), new Insets(0));
        BackgroundFill bgWhite = new BackgroundFill(Color.valueOf("WHITE"), new CornerRadii(4), new Insets(0));
        if(l==3) {
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgWhite));
            letters5.setBackground(new Background(bgWhite));

            letters3.setPrefSize(35,35);
            letters4.setPrefSize(30,30);
            letters5.setPrefSize(30,30);
        }
        else if(l==4) {
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgWhite));

            letters3.setPrefSize(30,30);
            letters4.setPrefSize(35,35);
            letters5.setPrefSize(30,30);
        }
        else if(l==5) {
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgRed));

            letters3.setPrefSize(30,30);
            letters4.setPrefSize(30,30);
            letters5.setPrefSize(35,35);
        }
    }

    void setDifficulty(int d){
        difficulty=d;
        if(d==1) {
            easy.setPrefSize(37,37);
            medium.setPrefSize(30,30);
            hard.setPrefSize(30,30);
        }
        else if(d==2) {
            easy.setPrefSize(30,30);
            medium.setPrefSize(35,35);
            hard.setPrefSize(30,30);
        }
        else if(d==3) {
            easy.setPrefSize(30,30);
            medium.setPrefSize(30,30);
            hard.setPrefSize(35,35);
        }
    }
}
