package com.example.miniproject_1b;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class HelloController {

    @FXML
    private ImageView fist;

    @FXML
    private ImageView five_hand;
    @FXML
    TextField run;

    @FXML
    protected void onHelloButtonClick() {
        KeyValue top=new KeyValue(fist.translateXProperty(),80);
        KeyValue turn=new KeyValue(fist.rotateProperty(),70);
        KeyValue side=new KeyValue(fist.translateYProperty(),-70);
        KeyFrame shake=new KeyFrame(Duration.seconds(0.15),top,turn,side);

        KeyValue down=new KeyValue(fist.translateXProperty(),0);
        KeyValue back=new KeyValue(fist.translateYProperty(),0);
        KeyValue straight=new KeyValue(fist.rotateProperty(),0);

        //Image hand=setImage();
        KeyValue hideFist=new KeyValue(fist.visibleProperty(),false);
        KeyValue showHand=new KeyValue(five_hand.visibleProperty(),true);
        KeyValue showFist=new KeyValue(fist.visibleProperty(),true);
        KeyValue hideHand=new KeyValue(five_hand.visibleProperty(),false);

        KeyFrame play=new KeyFrame(Duration.millis(500),hideFist,down,back,straight,showHand);
        KeyFrame unplay=new KeyFrame(Duration.millis(700),hideHand,showFist);
        Timeline reset=new Timeline(unplay);

        Timeline updown=new Timeline(shake);
        updown.setCycleCount(6);
        updown.setAutoReverse(true);
        updown.play();
        run.setEditable(true);

        Timeline finalMove=new Timeline(shake,play);
        updown.setOnFinished(e-> {
            finalMove.play();
            run.setEditable(false);
            //clip.play();
            //mediaPlayer.play();
        });
        finalMove.setOnFinished(e->reset.play());
    }

   /* Image setImage(){
        //Image hands[]=new Image[5];
        int no=(int)(Math.random()*6);
        Image hand=new Image("@tilted_fist.png");
        //hand.s
        return hand;
    }*/
}