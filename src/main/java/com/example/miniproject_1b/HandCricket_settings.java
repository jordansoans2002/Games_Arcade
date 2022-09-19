package com.example.miniproject_1b;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class HandCricket_settings extends Application {

    public Button overs3;
    @FXML
    void setOvers3(){
        setGameLength(3);
    }
    public Button overs5;
    @FXML
    void setOvers5(){
        setGameLength(5);
    }
    public Button overs10;
    @FXML
    void setOvers10(){
        setGameLength(10);
    }
    public Button oversU;
    @FXML
    void setOversU(){ setGameLength(50); }

    public Button easy;
    @FXML
    void setEasy(){
        setDifficulty(0);
    }
    public Button medium;
    @FXML
    void setMedium(){
        setDifficulty(1);
    }
    public Button hard;
    @FXML
    void setHard(){
        setDifficulty(2);
    }

    public Button wickets1;
    @FXML
    void setWickets1(){
        setNoOfWickets(1);
    }
    public Button wickets3;
    @FXML
    void setWickets3(){
        setNoOfWickets(3);
    }
    public Button wickets5;
    @FXML
    void setWickets5(){
        setNoOfWickets(5);
    }
    public Button wickets10;
    @FXML
    void setWickets10(){ setNoOfWickets(10); }


    public Label ipAddress;
    public TextField serverIP;
    @FXML
    void setServer(){
        try {
            InetAddress localhost=InetAddress.getLocalHost();
            String ipAdd=localhost.getHostAddress();
            ipAddress.setText(ipAdd);
            ipAddress.setVisible(true);
            serverIP.setVisible(false);
            multiPlayer =true;
            server=true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void setClient(){
        ipAddress.setVisible(false);
        serverIP.setVisible(true);
        multiPlayer =true;
        server=false;
    }
    @FXML
    void setSinglePC(){
        multiPlayer =false;
        ipAddress.setVisible(false);
        serverIP.setVisible(false);
    }

    static boolean server=true;
    static boolean multiPlayer =false;
    static int difficulty=1;
    static int gameLength=50;
    static int noOfWickets=1;

    static void main(String []args){launch();}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(CowBull_settings.class.getResource("HandCricket_settings.fxml"));
        Scene settingsScene=new Scene(fxmlLoader.load());
        stage.setScene(settingsScene);
        stage.setTitle("Settings");
        stage.show();
    }

    @FXML
    void startGame() {
        if(multiPlayer){
            HandCricket_networking.host=serverIP.getText();
            new HandCricket_networking().start();
        }
        else
            HandCricket.batting = (int)(Math.random()*2)==0;

        try {
            HandCricket_controller.startGame();
        }catch (IOException e){
            //todo
        }
    }

    double normal=33,selected=37;
    void setGameLength(int l){
        gameLength =l;
        if(l==3) {
            overs3.setPrefSize(selected,selected);
            overs5.setPrefSize(normal,normal);
            overs10.setPrefSize(normal,normal);
            oversU.setPrefSize(normal,normal);
        }
        else if(l==5) {
            overs3.setPrefSize(normal,normal);
            overs5.setPrefSize(selected,selected);
            overs10.setPrefSize(normal,normal);
            oversU.setPrefSize(normal,normal);
        }
        else if(l==10) {
            overs3.setPrefSize(normal,normal);
            overs5.setPrefSize(normal,normal);
            overs10.setPrefSize(selected,selected);
            oversU.setPrefSize(normal,normal);
        }
        else if(l==50) {
            overs3.setPrefSize(normal,normal);
            overs5.setPrefSize(normal,normal);
            overs10.setPrefSize(normal,normal);
            oversU.setPrefSize(selected,selected);
        }
    }

    void setDifficulty(int d){
        difficulty=d;
        if(d==0) {
            easy.setPrefSize(selected,selected);
            medium.setPrefSize(normal,normal);
            hard.setPrefSize(normal,normal);
        }
        else if(d==1) {
            easy.setPrefSize(normal,normal);
            medium.setPrefSize(selected,selected);
            hard.setPrefSize(normal,normal);
        }
        else if(d==2) {
            easy.setPrefSize(normal,normal);
            medium.setPrefSize(normal,normal);
            hard.setPrefSize(selected,selected);
        }
    }

    void setNoOfWickets(int l){
        noOfWickets =l;
        if(l==1) {
            wickets1.setPrefSize(selected,selected);
            wickets3.setPrefSize(normal,normal);
            wickets5.setPrefSize(normal,normal);
            wickets10.setPrefSize(normal,normal);
        }
        else if(l==3) {
            wickets1.setPrefSize(normal,normal);
            wickets3.setPrefSize(selected,selected);
            wickets5.setPrefSize(normal,normal);
            wickets10.setPrefSize(normal,normal);
        }
        else if(l==5) {
            wickets1.setPrefSize(normal,normal);
            wickets3.setPrefSize(normal,normal);
            wickets5.setPrefSize(selected,selected);
            wickets10.setPrefSize(normal,normal);
        }
        else if(l==10) {
            wickets1.setPrefSize(normal,normal);
            wickets3.setPrefSize(normal,normal);
            wickets5.setPrefSize(normal,normal);
            wickets10.setPrefSize(selected,selected);
        }
    }
}
