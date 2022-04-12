package com.example.miniproject_1b;

import java.util.Scanner;

public class HandCricket {
    static int totalRuns=0,ballsBowled=0,innings=1,target;
    public static void main(String[] args) {
        //handCricket_server.main();
        handCricket_client.main();
        System.out.println("LETS START THE GAME");
        System.out.println("HAND CRICKET");
        //int toss=(int)(Math.random()*2);
        handCricket_server.send(toss)
        int toss=handCricket_client.receive();
        if(toss==1) //if(toss == 0)
            batting();
        else
            bowling();
    }
    public static void batting(){
        totalRuns=0;
        ballsBowled=0;
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("ENTER RUN");
            int run = sc.nextInt();
            if(run<0 || run>6)
            {
                System.out.println("INVALID INPUT");
                continue;
            }
            handCricket_client.send(run);

            int ball = handCricket_client.receive();
            System.out.println(ball);
            if (run == ball) {
                System.out.println("OUT");
                if(innings==1){
                    target=totalRuns;
                    System.out.println("defend: "+target);
                    innings=2;
                    bowling();
                }
                else
                    System.out.println("you lost");
                break;
            }
            totalRuns = totalRuns + run;
            ballsBowled++;
            if(innings==2&&totalRuns>=target){
                System.out.println("you won");
                break;
            }
            if(ballsBowled%6==0)
                System.out.println("OVERS COMPLETED "+ballsBowled/6 );
            System.out.println("TOTAL RUNS =" + totalRuns);
            System.out.println("TOTAL BALLS =" + ballsBowled);
        }

    }

    public static void bowling(){
        totalRuns=0;
        ballsBowled=0;
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("ENTER BALL");
            int ball = sc.nextInt();
            if(ball<0 || ball>6)
            {
                System.out.println("INVALID INPUT");
                continue;
            }
            handCricket_client.send(ball);

            int runs = handCricket_client.receive();
            System.out.println(runs);
            if (runs == ball) {
                System.out.println("OUT");
                if(innings==1){
                    target=totalRuns;
                    System.out.println("target: "+target);
                    innings=2;
                    batting();
                }
                break;
            }
            totalRuns = totalRuns + runs;
            ballsBowled++;
            if(innings==2&&totalRuns>target){
                System.out.println("you lost");
                break;
            }
            if(ballsBowled%6==0)
                System.out.println("OVERS COMPLETED "+ballsBowled/6 );
            System.out.println("TOTAL RUNS =" + totalRuns);
            System.out.println("TOTAL BALLS =" + ballsBowled);
        }
    }

}
