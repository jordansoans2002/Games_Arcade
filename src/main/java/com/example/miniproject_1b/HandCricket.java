package com.example.miniproject_1b;

public class HandCricket {
    static int totalRuns=0,runsInOver=0,ballsBowled=0,innings=1,target,max=6,wickets=0;
    static boolean server=true,batting=false;

    static boolean score(int n,int inMsg){
        //int run = batting? n:HandCricket_networking.receive();
        //int ball = batting? HandCricket_networking.receive():n;
        int run = batting? n:inMsg;
        int ball = batting? inMsg:n;

        if(run==ball || HandCricket_settings.gameLength == ballsBowled*6){
            if(run==ball) {
                System.out.println("OUT");
                wickets++;
            }
            if(innings==1) {
                if(HandCricket_settings.gameLength==ballsBowled*6 || HandCricket_settings.noOfWickets==wickets) {
                    innings++;
                    batting = !batting;
                    target = totalRuns + 1;
                    totalRuns = 0;
                    runsInOver = 0;
                    ballsBowled = 0;
                    wickets = 0;
                    System.out.println("target set is " + target);
                }
            }
            else {
                if(HandCricket_settings.gameLength==ballsBowled*6 ||wickets==HandCricket_settings.noOfWickets) {
                    if (batting) {
                        System.out.println("you LOST");
                        HandCricket_controller.gameOver = true;
                        HandCricket_settings.winner = false;
                    } else {
                        System.out.println("you WON");
                        HandCricket_controller.gameOver = true;
                        HandCricket_settings.winner = true;
                    }
                }
            }
            return true;
        }

        totalRuns += run;
        ballsBowled++;
        runsInOver += run;
        System.out.println("runs "+totalRuns);

        if(ballsBowled%6==0)
            System.out.println("runs in this over "+runsInOver);

        if(innings==2 && totalRuns>=target){
            if(batting) {
                System.out.println("you WON");
                HandCricket_controller.gameOver=true;
                HandCricket_settings.winner=true;
            }
            else {
                System.out.println("you LOST");
                HandCricket_controller.gameOver=true;
                HandCricket_settings.winner = false;
            }
        }

        return false;
    }

    //for testing without GUI
    /*public static void play(){
        Scanner sc=new Scanner(System.in);
        int input=0;
        do {
            try {
                do {
                    if (batting)
                        System.out.print("enter runs ");
                    else
                        System.out.print("enter ball ");

                    input = sc.nextInt();
                    if (input > max || input < 0) {
                        System.out.println("invalid input");
                        continue;
                    }
                    break;
                }while(true);
            } catch (InputMismatchException e) {
                continue;
            }
            HandCricket_networking.send(input);
        }while (score(input));
    }*/
}