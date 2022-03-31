package com.example.miniproject_1b;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandCricket {

    int target=0,innings=0;
    int totalRuns=0,runsInOver=0,ballsInOver=1,overs=0;
    int run,ball;
    boolean batting=false,toTen=true;
    char key;
    @FXML
    AnchorPane anchor;
    @FXML
    HBox runsInOverBox;
    @FXML
    HBox ballsInOverBox;

    void main(){
        anchor.setOnKeyTyped(e -> {
            key=e.getText().charAt(0);
            if(isValidKey()){
                run=getRun();
                ball=getBall();
                if(run==ball)
                    batsmanOut();

                runsInOver+=run;
                totalRuns+=run;
                if(innings==2 && totalRuns>target)
                    targetAchieved();

                ballsInOver++;
                if(ballsInOver==6){
                    runsInOver=0;
                    ballsInOver=1;
                    overs++;
                }
            }
        });
    }
    boolean isValidKey(){
        if(toTen&&Character.isDigit(key))
            return true;
        if(!toTen&&Character.isLetter(key))
            return true;
        return false;

    }

    int getBall(){
        if(batting)
           return (int)(Math.random()*10);
        else
            return Integer.parseInt(String.valueOf(key));
    }
    int getRun(){
        if(!batting)
            return (int)(Math.random()*10);
        else
            return Integer.parseInt(String.valueOf(key));
    }

    void batsmanOut(){
        if(innings==1) {
            target = totalRuns;
            System.out.println("target = " + totalRuns);
        }
        else if(innings==2 && totalRuns==target)
            System.out.println("draw");
        else
            System.out.println("lost");
    }
    void targetAchieved(){
        System.out.println("You won");
    }


}
