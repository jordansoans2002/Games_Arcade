package com.example.miniproject_1b;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

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

    public Label ipAddress;
    public TextField serverIP;
    @FXML
    void setServer(){
        ServerSocket serverSocket;
        try {
            serverSocket=new ServerSocket(5555);
            CowBull_controller.playerNo=0;
            //ipAddress.setText(String.valueOf(serverSocket.));
            ipAddress.setText("my IP address");
            ipAddress.setVisible(true);
            serverIP.setVisible(false);
            multiPC =true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void setClient(){
        CowBull_controller.playerNo=1;
        ipAddress.setVisible(false);
        serverIP.setVisible(true);
        multiPC =true;
    }
    @FXML
    void setHost(){
        multithread_client.host=serverIP.getText();
    }
    @FXML
    void setSinglePC(){
        CowBull_controller.playerNo=0;
        multiPC =false;
        ipAddress.setVisible(false);
        serverIP.setVisible(false);
    }

    static String target="SNOW";
    static int noOfPlayers=1;
    static boolean multiPC =true;
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
        target=CowBull_dictionary.getWord(wordLength);
        CowBull_controller.startGame();
        //clientController.startGame();
    }

    double normal=33,selected=37;
    void setWordLength(int l){
        wordLength=l;
        BackgroundFill bgOrange = new BackgroundFill(Color.valueOf("ORANGE"), new CornerRadii(4), new Insets(0));
        BackgroundFill bgWhite = new BackgroundFill(Color.valueOf("WHITE"), new CornerRadii(4), new Insets(0));
        if(l==3) {
            //target="NOW";
            letters3.setBackground(new Background(bgOrange));
            letters4.setBackground(new Background(bgWhite));
            letters5.setBackground(new Background(bgWhite));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(selected,selected);
            letters4.setPrefSize(normal,normal);
            letters5.setPrefSize(normal,normal);
            letters6.setPrefSize(normal,normal);
        }
        else if(l==4) {
            //target="SNOW";
            letters3.setBackground(new Background(bgOrange));
            letters4.setBackground(new Background(bgOrange));
            letters5.setBackground(new Background(bgWhite));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(normal,normal);
            letters4.setPrefSize(selected,selected);
            letters5.setPrefSize(normal,normal);
            letters6.setPrefSize(normal,normal);
        }
        else if(l==5) {
            //target="SNOWY";
            letters3.setBackground(new Background(bgOrange));
            letters4.setBackground(new Background(bgOrange));
            letters5.setBackground(new Background(bgOrange));
            letters6.setBackground(new Background(bgWhite));

            letters3.setPrefSize(normal,normal);
            letters4.setPrefSize(normal,normal);
            letters5.setPrefSize(selected,selected);
            letters6.setPrefSize(normal,normal);
        }
        else if(l==6) {
            //target="SNOWEY";
            letters3.setBackground(new Background(bgOrange));
            letters4.setBackground(new Background(bgOrange));
            letters5.setBackground(new Background(bgOrange));
            letters6.setBackground(new Background(bgOrange));

            letters3.setPrefSize(normal,normal);
            letters4.setPrefSize(normal,normal);
            letters5.setPrefSize(normal,normal);
            letters6.setPrefSize(selected,selected);
        }
    }

    void setDifficulty(int d){
        difficulty=d;
        if(d==1) {
            easy.setPrefSize(selected,selected);
            medium.setPrefSize(normal,normal);
            hard.setPrefSize(normal,normal);
        }
        else if(d==2) {
            easy.setPrefSize(normal,normal);
            medium.setPrefSize(selected,selected);
            hard.setPrefSize(normal,normal);
        }
        else if(d==3) {
            easy.setPrefSize(normal,normal);
            medium.setPrefSize(normal,normal);
            hard.setPrefSize(selected,selected);
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

            /*players1.setPrefSize(selected,selected);
            players2.setPrefSize(normal,normal);
            players3.setPrefSize(normal,normal);
            players4.setPrefSize(normal,normal);
            players5.setPrefSize(normal,normal);*/
        }
        else if(n==2) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgWhite));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            /*players1.setPrefSize(normal,normal);
            players2.setPrefSize(selected,selected);
            players3.setPrefSize(normal,normal);
            players4.setPrefSize(normal,normal);
            players5.setPrefSize(normal,normal);*/
        }
        else if(n==3) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgWhite));
            players5.setBackground(new Background(bgWhite));

            /*players1.setPrefSize(normal,normal);
            players2.setPrefSize(normal,normal);
            players3.setPrefSize(selected,selected);
            players4.setPrefSize(normal,normal);
            players5.setPrefSize(normal,normal);*/
        }
        else if(n==4) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgWhite));

            /*players1.setPrefSize(normal,normal);
            players2.setPrefSize(normal,normal);
            players3.setPrefSize(normal,normal);
            players4.setPrefSize(selected,selected);
            players5.setPrefSize(normal,normal);*/
        }
        else if(n==5) {
            players1.setBackground(new Background(bgGreen));
            players2.setBackground(new Background(bgGreen));
            players3.setBackground(new Background(bgGreen));
            players4.setBackground(new Background(bgGreen));
            players5.setBackground(new Background(bgGreen));

            /*players1.setPrefSize(normal,normal);
            players2.setPrefSize(normal,normal);
            players3.setPrefSize(normal,normal);
            players4.setPrefSize(normal,normal);
            players5.setPrefSize(selected,selected);*/
        }
    }
}
