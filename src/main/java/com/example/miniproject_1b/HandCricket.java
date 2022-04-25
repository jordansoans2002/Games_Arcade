package com.example.miniproject_1b;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HandCricket {
    static int totalRuns=0,runsInOver=0,ballsBowled=0,innings=1,target,max=6;
    static boolean server=false,batting=false,winner;

    public static void main() {
        System.out.println("LETS START THE GAME");
        System.out.println("HAND CRICKET");
        new HandCricket_networking().start();
        System.out.println("server started");
        int toss;
        if(server){
            toss=(int)(Math.random()*2);
            HandCricket_networking.send(toss);
            //toss=1;
            batting = toss==0;
        }
        else{
            toss=HandCricket_networking.inMsg;
            //toss=0;
            batting = toss==1;
        }
    }

    static boolean score(int n){
        //int run = batting? n:HandCricket_networking.receive();
        //int ball = batting? HandCricket_networking.receive():n;
        HandCricket_networking.send(n);
        int run = batting? n:HandCricket_networking.inMsg;
        int ball = batting? HandCricket_networking.inMsg:n;

        if(run==ball){
            System.out.println("OUT");
            if(innings==1) {
                innings++;
                batting=!batting;
                target=totalRuns+1;
                totalRuns=0;
                runsInOver=0;
                ballsBowled=0;
                System.out.println("target set is " + target);
                return true;
            }
            else {
                if(batting) {
                    System.out.println("you LOST");
                    winner=false;
                }
                else {
                    System.out.println("you WON");
                    winner = true;
                }
                return false;
            }
        }

        totalRuns += run;
        ballsBowled++;
        runsInOver += run;
        System.out.println("runs "+totalRuns+" runs in over "+runsInOver);

        if(ballsBowled%6==0) {
            System.out.println("over completed");
            System.out.println("runs in this over "+runsInOver);
            runsInOver = 0;
        }

        if(innings==2 && totalRuns>=target){
            if(batting) {
                System.out.println("you WON");
                winner=true;
            }
            else {
                System.out.println("you LOST");
                winner = false;
            }
            return false;
        }
        return true;
    }

    //for testing without GUI
    public static void play(){
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
    }
}