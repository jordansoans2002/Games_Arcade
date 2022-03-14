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
import java.util.Arrays;
import java.util.Objects;

public class CowBull_settings extends Application {

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


    static CowBull[] ob;
    static String target="SNOW";
    static int noOfPlayers=1;
    static int difficulty=2;
    static int wordLength=4;
    static int[] points;

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
        Scene scene=new Scene(fxmlLoader.load());
        window.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(CowBull_test.class.getResource("CowBull.css")).toExternalForm());

        HBox multiplayer=new HBox(20);
        ob =new CowBull[noOfPlayers];
        points=new int[noOfPlayers];
        for(int i=0;i<noOfPlayers;i++){
            ob[i]=new CowBull();
            ob[i].player=i;
            //multiplayer.getChildren().add(ob[i].main());
        }
        //Scene gameScene=new Scene(multiplayer);
        //gameScene.getStylesheets().add(Objects.requireNonNull(CowBull_test.class.getResource("CowBull.css")).toExternalForm());
        //window.setScene(gameScene);

        window.setTitle("CowBull game");
        window.show();
    }

    void setWordLength(int l){
        wordLength=l;
        BackgroundFill bgRed = new BackgroundFill(Color.valueOf("RED"), new CornerRadii(4), new Insets(0));
        BackgroundFill bgWhite = new BackgroundFill(Color.valueOf("WHITE"), new CornerRadii(4), new Insets(0));
        if(l==3) {
            target="NOW";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgWhite));
            letters5.setBackground(new Background(bgWhite));

            letters3.setPrefSize(35,35);
            letters4.setPrefSize(30,30);
            letters5.setPrefSize(30,30);
        }
        else if(l==4) {
            target="SNOW";
            letters3.setBackground(new Background(bgRed));
            letters4.setBackground(new Background(bgRed));
            letters5.setBackground(new Background(bgWhite));

            letters3.setPrefSize(30,30);
            letters4.setPrefSize(35,35);
            letters5.setPrefSize(30,30);
        }
        else if(l==5) {
            target="SNOWY";
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

            players1.setPrefSize(35,35);
            players2.setPrefSize(30,30);
            players3.setPrefSize(30,30);
            players4.setPrefSize(30,30);
            players5.setPrefSize(30,30);
        }
        else if(n==2) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgWhite));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(30,30);
            players2.setPrefSize(35,35);
            players3.setPrefSize(30,30);
            players4.setPrefSize(30,30);
            players5.setPrefSize(30,30);
        }
        else if(n==3) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(30,30);
            players2.setPrefSize(30,30);
            players3.setPrefSize(35,35);
            players4.setPrefSize(30,30);
            players5.setPrefSize(30,30);
        }
        else if(n==4) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgWhite));

            players1.setPrefSize(30,30);
            players2.setPrefSize(30,30);
            players3.setPrefSize(30,30);
            players4.setPrefSize(35,35);
            players5.setPrefSize(30,30);
        }
        else if(n==5) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgGreen));

            players1.setPrefSize(30,30);
            players2.setPrefSize(30,30);
            players3.setPrefSize(30,30);
            players4.setPrefSize(30,30);
            players5.setPrefSize(35,35);
        }
    }

    static void getPoints(){
        for(int i=0;i<noOfPlayers;i++) {
            points[i] = ob[i].points;
            //ob[i].enterGuesses.setEditable(false);
        }
        System.out.println(Arrays.stream(points).sorted());
    }
}
