package com.example.miniproject_1b;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class HandCricket_controller {


    static boolean takeInput =false,gameLoop =false,gameOver=false,winner;
    static boolean invalidInput;
    static boolean ready=false,interrupt;
    static int input,inMsg;
    static String status="toss";

    public ImageView umpire;
    public Image wait;
    public Image play;
    public Image out;
    public ImageView foreHand;
    public ImageView backHand;
    public Image nine;
    public Image eight;
    public Image seven;
    public Image six;
    public Image five;
    public Image four;
    public Image three;
    public Image two;
    public Image one;
    public Image zero;

    public Label balls_wickets;
    public Label innings;
    public Label mathLabel;
    public TextField mathInput;
    public ImageView trophy;
    public Label objective;


    public static void startGame() throws IOException {
        FXMLLoader field= new FXMLLoader(HandCricket_controller.class.getResource("HandCricket.fxml"));
        Scene scene=new Scene(field.load());

        scene.setOnKeyPressed(e->{
            if(takeInput) {
                try {
                    int n = Integer.parseInt(e.getText());
                    if (n <= 6 && n >= 0) {
                        input = n;
                        invalidInput=false;
                    }
                    else
                        invalidInput=true;
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(e+" "+e.getCode()+" "+e.getText());
                }
            }
        });

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(e-> {
                    HandCricket_networking.closeChat();
                    takeInput =false;
                    gameLoop =false;
                    gameOver=false;

                    HandCricket.totalRuns = 0;
                    HandCricket.runsInOver = 0;
                    HandCricket.ballsBowled = 0;
                    HandCricket.innings = 1;
                    HandCricket.target = 0;
                    HandCricket.wickets = 0;
        });
        stage.show();
    }

    @FXML
    public void mainAnimation(){
        status="started";
        startAnimation();
        Timeline loop=new Timeline(new KeyFrame(Duration.seconds(1)));
        loop.setOnFinished(e->{
            if(interrupt) {
                startAnimation();
                interrupt=false;
            }
            if(!gameOver)
                loop.play();
        });
        loop.play();
    }
    @FXML
    public void setScene(){
        if(gameLoop|| gameOver)
            return;
        if(!HandCricket_settings.multiPlayer||ready) {
            if (HandCricket.batting) {

                if(HandCricket.innings==1) {
                    if (HandCricket_settings.difficulty == 2)
                        objective.setText("Find the sum of the total runs");
                    else if (HandCricket_settings.difficulty == 1)
                        objective.setText("Find the sum of the runs scored in this over");
                    else
                        objective.setText("Choose a number different from the bowler to score maximum runs");
                }
                else{
                    if (HandCricket_settings.difficulty == 0)
                        objective.setText("Choose a number different from the bowler to achieve the target");
                    else
                        objective.setText("Find the number of runs required to win");
                }

                foreHand.setLayoutX(321.0);
                foreHand.setLayoutY(285.0);
                backHand.setLayoutX(340.0);
                backHand.setLayoutY(171.0);
                umpire.setLayoutX(293.0);
                umpire.setLayoutY(147.0);
                umpire.setFitHeight(70.0);
                umpire.setFitWidth(56.0);
            } else {
                objective.setText("Choose the same number as the batsman to make him out");
                foreHand.setLayoutX(349.0);
                foreHand.setLayoutY(310.0);
                backHand.setLayoutX(309.0);
                backHand.setLayoutY(166.0);
                umpire.setLayoutX(274.0);
                umpire.setLayoutY(244.0);
                umpire.setFitHeight(105.0);
                umpire.setFitWidth(74.0);
            }

            innings.setText( (HandCricket.innings == 1? "1st Innings" : "Target is " + HandCricket.target) +  (HandCricket.batting? " (Batting)" : " (Bowling)"));

            umpire.setImage(wait);
            umpire.setVisible(true);
            foreHand.setVisible(true);
            backHand.setVisible(true);
            mathLabel.setVisible(false);
            mathInput.setVisible(false);
            //mainAnimation();
        }
    }

    @FXML
    protected void startAnimation() {
        setScene();
        if(HandCricket_settings.difficulty>0)
            balls_wickets.setText(HandCricket.wickets +" / "+ HandCricket.overs +"."+ HandCricket.ballsBowled%6);
        else
            balls_wickets.setText(HandCricket.totalRuns +"-"+ HandCricket.wickets +" ("+ HandCricket.overs +"."+ HandCricket.ballsBowled%6 +")");
        gameLoop = !gameLoop;
        /*if(HandCricket_settings.multiPlayer)
        {
            if(gameLoop)
                HandCricket_networking.send(-1);
            else
                HandCricket_networking.send(-2);
        }*/

        Image[] numbers={zero,one,two,three,four,five,six,seven,eight,nine};
        KeyValue foreTurn0 = new KeyValue(foreHand.rotateProperty(), 0);
        KeyValue foreTurn1 = new KeyValue(foreHand.rotateProperty(), 23);
        KeyValue foreTurn2 = new KeyValue(foreHand.rotateProperty(), 35);
        KeyValue foreTurn3 = new KeyValue(foreHand.rotateProperty(), 50);

        KeyValue foreMoveX0 = new KeyValue(foreHand.translateXProperty(),0);
        KeyValue foreMoveX1 = new KeyValue(foreHand.translateXProperty(),6);
        KeyValue foreMoveX2 = new KeyValue(foreHand.translateXProperty(),25);
        KeyValue foreMoveX3 = new KeyValue(foreHand.translateXProperty(),56);

        KeyValue foreMoveY0 = new KeyValue(foreHand.translateYProperty(),0);
        KeyValue foreMoveY1 = new KeyValue(foreHand.translateYProperty(),-6);
        KeyValue foreMoveY2 = new KeyValue(foreHand.translateYProperty(),-25);
        KeyValue foreMoveY3 = new KeyValue(foreHand.translateYProperty(),-67);

        KeyValue backTurn0 = new KeyValue(backHand.rotateProperty(), 0);
        KeyValue backTurn1 = new KeyValue(backHand.rotateProperty(), 23);
        KeyValue backTurn2 = new KeyValue(backHand.rotateProperty(), 35);
        KeyValue backTurn3 = new KeyValue(backHand.rotateProperty(), 50);

        KeyValue backMoveX0 = new KeyValue(backHand.translateXProperty(),0);
        KeyValue backMoveX1 = new KeyValue(backHand.translateXProperty(),6);
        KeyValue backMoveX2 = new KeyValue(backHand.translateXProperty(),25);
        KeyValue backMoveX3 = new KeyValue(backHand.translateXProperty(),56);

        KeyValue backMoveY0 = new KeyValue(backHand.translateYProperty(),0);
        KeyValue backMoveY1 = new KeyValue(backHand.translateYProperty(),-6);
        KeyValue backMoveY2 = new KeyValue(backHand.translateYProperty(),-25);
        KeyValue backMoveY3 = new KeyValue(backHand.translateYProperty(),-67);

        KeyValue readyUmp=new KeyValue(umpire.imageProperty(),wait);
        KeyValue playUmp=new KeyValue(umpire.imageProperty(), play);

        KeyFrame play=new KeyFrame(Duration.seconds(0.2),playUmp);
        KeyFrame stopPlay=new KeyFrame(Duration.seconds(0.1),readyUmp);

        KeyFrame mvtUp=new KeyFrame(Duration.seconds(0.15),foreTurn1,foreMoveX1,foreMoveY1,backTurn1,backMoveX1,backMoveY1,foreTurn2,foreMoveX2,foreMoveY2,backTurn2,backMoveX2,backMoveY2);
        KeyFrame mvtDn=new KeyFrame(Duration.seconds(0.15),foreTurn1,foreMoveX1,foreMoveY1,backTurn1,backMoveX1,backMoveY1,foreTurn0,foreMoveX0,foreMoveY0,backTurn0,backMoveX0,backMoveY0);

        KeyFrame showUp=new KeyFrame(Duration.seconds(0.2),foreTurn1,foreMoveX1,foreMoveY1,backTurn1,backMoveX1,backMoveY1,foreTurn2,foreMoveX2,foreMoveY2,backTurn2,backMoveX2,backMoveY2,foreTurn3,foreMoveX3,foreMoveY3,backTurn3,backMoveX3,backMoveY3);
        KeyFrame showDn=new KeyFrame(Duration.seconds(0.3),foreTurn2,foreMoveX2,foreMoveY2,backTurn2,backMoveX2,backMoveY2,foreTurn1,foreMoveX1,foreMoveY1,backTurn1,backMoveX1,backMoveY1,foreTurn0,foreMoveX0,foreMoveY0,backTurn0,backMoveX0,backMoveY0);

        Timeline startMove=new Timeline(play);
        startMove.setDelay(Duration.seconds(0.2)); //delay before game start
        Timeline stopMove=new Timeline(stopPlay);
        Timeline moveUp=new Timeline(mvtUp);
        Timeline moveDown=new Timeline(mvtDn);
        Timeline showingUp=new Timeline(showUp);
        Timeline showDown=new Timeline(showDn);
        Timeline reset=new Timeline(new KeyFrame(Duration.seconds(0.2),new KeyValue(foreHand.imageProperty(),zero),new KeyValue(backHand.imageProperty(),zero)));
        reset.setDelay(Duration.seconds(0.5));

        startMove.setOnFinished(e->{
            takeInput=true;
            moveUp.play();
        });
        AtomicInteger n= new AtomicInteger(3);
        moveUp.setOnFinished(e ->{
            n.getAndDecrement();
            moveDown.play();
        });
        moveDown.setOnFinished(e->{
            if(n.get() > 0)
                moveUp.play();
            else {
                showingUp.play();
            }
        });
        showingUp.setOnFinished(e->{
            if(HandCricket_settings.multiPlayer) {
                HandCricket_networking.send(input);
                inMsg = HandCricket_networking.inMsg;
            }
            else
                inMsg=2;//(int)(Math.random()*7); //to seed the computers number

            if (HandCricket.scoreBoard(input,inMsg)) {
                mathLabel.setText("Innings completed");
                gameLoop=false;
                mathLabel.setVisible(true);
                umpire.setImage(wait);
            }
            if(status.equalsIgnoreCase("wicket")){
                status="playing";
                mathLabel.setText("Out");
                mathLabel.setVisible(true);
                gameLoop=false;
            }

            stopMove.play();
        });

        stopMove.setOnFinished(e->{
            if(invalidInput)
                foreHand.setOpacity(0.5);
            else
                foreHand.setOpacity(1);

            KeyValue showFore=new KeyValue(foreHand.imageProperty(),numbers[input]);
            KeyValue showBack=new KeyValue(backHand.imageProperty(),numbers[inMsg]);
            KeyFrame show=new KeyFrame(Duration.seconds(0.2),showFore,showBack);
            Timeline showHand=new Timeline(show);
            takeInput=false;
            showDown.play();
            showHand.play();
        });
        showDown.setOnFinished(e->reset.play());
        reset.setOnFinished(e->{
            input=0;
            foreHand.setOpacity(1);
            n.set(3);
            if(HandCricket_settings.difficulty>0)
                balls_wickets.setText(HandCricket.wickets +" / "+ HandCricket.overs +"."+ HandCricket.ballsBowled%6);
            else
                balls_wickets.setText(HandCricket.totalRuns +"-"+ HandCricket.wickets +" ("+ HandCricket.overs +"."+ HandCricket.ballsBowled%6 +")");

            if(HandCricket.ballsBowled%6==0 && HandCricket.ballsBowled!=0 && HandCricket.overs<HandCricket_settings.gameLength&& !gameOver && HandCricket.batting && HandCricket_settings.difficulty>0){
                gameLoop=false;
                showMath();
            }
            if(gameLoop && !gameOver)
                startMove.play();

            if(gameOver){
                if(winner) {
                    mathLabel.setText("You Won!!!");
                    trophy.setVisible(true);
                    umpire.setVisible(false);
                    foreHand.setVisible(false);
                    backHand.setVisible(false);
                    mathInput.setVisible(false);
                }
                else
                    mathLabel.setText("you lost");
                mathLabel.setVisible(true);
            }

        });
        if(gameLoop && !gameOver)
            startMove.playFromStart();
    }

    void showMath(){
        if(HandCricket.batting) {
            if (HandCricket.innings == 1){
                if(HandCricket_settings.difficulty ==1)
                    mathLabel.setText("Enter runs scored in this over");
                if(HandCricket_settings.difficulty ==2)
                    mathLabel.setText("Enter total runs");
            }
            else
                mathLabel.setText("Enter runs needed to win");

            mathLabel.setVisible(true);
            mathInput.setVisible(true);
            mathInput.setOnKeyPressed(e->{
                if(e.getCode() == KeyCode.ENTER){
                    try{
                        if(checkMath(Integer.parseInt(mathInput.getText()))){
                            HandCricket.runsInOver=0;
                            mathInput.setVisible(false);
                            mathLabel.setVisible(false);
                            startAnimation();
                        }
                        else {
                            mathLabel.setText("Run Out!!!");
                            mathInput.setVisible(false);
                            umpire.setImage(out);
                            HandCricket.scoreBoard(0, 0);
                        }
                        mathInput.clear();
                    }catch (NumberFormatException numberFormatException){
                        mathInput.clear();
                        mathInput.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderStroke.MEDIUM)));
                    }
                }
            });
        }
    }

    boolean checkMath(int n){
        if(HandCricket.innings==1){
            if(HandCricket_settings.difficulty ==1)
                return HandCricket.runsInOver == n;
            if(HandCricket_settings.difficulty == 2)
                return HandCricket.totalRuns == n;
        }
        else
            return (HandCricket.target-HandCricket.totalRuns)==n;

        return false;
    }
}
