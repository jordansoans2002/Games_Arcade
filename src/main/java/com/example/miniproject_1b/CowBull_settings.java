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

public class CowBull_settings extends Application {

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
    public Button letters6;
    @FXML
    void setLetters6(){ setWordLength(6); }

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

    public Button players1;
    @FXML
    void setPlayers1(){setNoOfPlayers(1);}
    public Button players2;
    @FXML
    void setPlayers2(){setNoOfPlayers(2);}
    public Button players3;
    @FXML
    void setPlayers3(){setNoOfPlayers(3);}
    public Button players4;
    @FXML
    void setPlayers4(){setNoOfPlayers(4);}
    public Button players5;
    @FXML
    void setPlayers5(){setNoOfPlayers(5);}

    static String target="SNOW";
    static int noOfPlayers=1;
    static int difficulty=2;
    static int wordLength=4;

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
    void startGame() {
        CowBull_controller.startGame();
        //clientController.startGame();
        
        target=CowBull_dictionary.getWord(wordLength);
    }

    void setWordLength(int l){
        wordLength=l;
        BackgroundFill bgRed = new BackgroundFill(Color.valueOf("RED"), new CornerRadii(4), new Insets(0));
        BackgroundFill bgWhite = new BackgroundFill(Color.valueOf("WHITE"), new CornerRadii(4), new Insets(0));
        if(l==3) {
            //target="NOW";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgWhite));
            letters5.setBackground(new Background(bgWhite));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(37,37);
            letters4.setPrefSize(33,33);
            letters5.setPrefSize(33,33);
            letters6.setPrefSize(33,33);
        }
        else if(l==4) {
            //target="SNOW";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgWhite));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(33,33);
            letters4.setPrefSize(37,37);
            letters5.setPrefSize(33,33);
            letters6.setPrefSize(33,33);
        }
        else if(l==5) {
            //target="SNOWY";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgRed));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(33,33);
            letters4.setPrefSize(33,33);
            letters5.setPrefSize(37,37);
            letters6.setPrefSize(33,33);
        }
        else if(l==6) {
            //target="SNOWEY";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgRed));
            letters6.setBackground(new Background(bgRed));

            letters3.setPrefSize(33,33);
            letters4.setPrefSize(33,33);
            letters5.setPrefSize(33,33);
            letters6.setPrefSize(37,37);
        }
    }

    void setDifficulty(int d){
        difficulty=d;
        if(d==1) {
            easy.setPrefSize(37,37);
            medium.setPrefSize(33,33);
            hard.setPrefSize(33,33);
        }
        else if(d==2) {
            easy.setPrefSize(33,33);
            medium.setPrefSize(37,37);
            hard.setPrefSize(33,33);
        }
        else if(d==3) {
            easy.setPrefSize(33,33);
            medium.setPrefSize(33,33);
            hard.setPrefSize(37,37);
        }
    }

    void setNoOfPlayers(int n){
        noOfPlayers=n;
        BackgroundFill bgGreen = new BackgroundFill(Color.valueOf("GREEN"), new CornerRadii(4), new Insets(0));
        BackgroundFill bgWhite = new BackgroundFill(Color.valueOf("WHITE"), new CornerRadii(4), new Insets(0));
        if(n==1) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgWhite));
            players3.setBackground(new Background(bgWhite));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(37,37);
            players2.setPrefSize(33,33);
            players3.setPrefSize(33,33);
            players4.setPrefSize(33,33);
            players5.setPrefSize(33,33);
        }
        else if(n==2) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgWhite));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(33,33);
            players2.setPrefSize(37,37);
            players3.setPrefSize(33,33);
            players4.setPrefSize(33,33);
            players5.setPrefSize(33,33);
        }
        else if(n==3) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(33,33);
            players2.setPrefSize(33,33);
            players3.setPrefSize(35,35);
            players4.setPrefSize(33,33);
            players5.setPrefSize(33,33);
        }
        else if(n==4) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(33,33);
            players2.setPrefSize(33,33);
            players3.setPrefSize(33,33);
            players4.setPrefSize(37,37);
            players5.setPrefSize(33,33);
        }
        else if(n==5) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgGreen));

            players1.setPrefSize(33,33);
            players2.setPrefSize(33,33);
            players3.setPrefSize(33,33);
            players4.setPrefSize(33,33);
            players5.setPrefSize(37,37);
        }
    }
}
