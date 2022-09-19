package com.example.miniproject_1b;

public class HandCricket {
    static int totalRuns=0,runsInOver=0,ballsBowled=0,overs,innings=1,target,max=6,wickets=0;
    static boolean server=true,batting=false;

    //returns true if innings is completed or all out
    static boolean scoreBoard(int n, int inMsg){
        //int run = batting? n:HandCricket_networking.receive();
        //int ball = batting? HandCricket_networking.receive():n;
        int run = batting? n:inMsg;
        int ball = batting? inMsg:n;

        ballsBowled++;
        if(ballsBowled%6==0)
            overs++;
        if(run==ball) {
            HandCricket_controller.status="wicket";
            wickets++;
        }
        else {
            totalRuns += run;
            runsInOver += run;
        }

        if(HandCricket_settings.gameLength==overs || HandCricket_settings.noOfWickets==wickets) {
            if (innings == 1) {
                innings++;
                batting = !batting;
                target = totalRuns + 1;
                totalRuns = 0;
                runsInOver = 0;
                ballsBowled = 0;
                overs = 0;
                wickets = 0;
                System.out.println("target set is " + target);
            } else {
                    if (batting) {
                        System.out.println("you LOST");
                        HandCricket_controller.gameOver = true;
                        HandCricket_controller.winner = false;
                    } else {
                        System.out.println("you WON");
                        HandCricket_controller.gameOver = true;
                        HandCricket_controller.winner = true;
                    }
            }
            HandCricket_controller.status="change";
            return true;
        }

        if(ballsBowled%6==0) {
            System.out.println("runs in this over " + runsInOver);
            System.out.println("total runs " + totalRuns);
        }


        if(innings==2 && totalRuns>=target){
            if(batting) {
                System.out.println("you WON");
                HandCricket_controller.gameOver=true;
                HandCricket_controller.winner=true;
            }
            else {
                System.out.println("you LOST");
                HandCricket_controller.gameOver=true;
                HandCricket_controller.winner = false;
            }
        }

        return false;
    }
}
