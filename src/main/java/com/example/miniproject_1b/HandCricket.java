package com.example.miniproject_1b;

import java.util.Scanner;

public class HandCricket {
    static int totalRuns=0,ballsBowled=0;
    public static void main(String[] args) {
        System.out.println("LETS START THE GAME");
        System.out.println("HAND CRICKET");
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("ENTER RUNS");
            int runs = sc.nextInt();
            if(runs<0 || runs>6)
            {
                System.out.println("INVALID INPUT");
                continue;
            }
            int ball = (int) (Math.random() * 7);
            System.out.println(ball);
            if (runs == ball) {
                System.out.println("OUT");
                break;
            }
            totalRuns = totalRuns + runs;
            ballsBowled++;
            if(ballsBowled%6==0)
                System.out.println("OVERS COMPLETED "+ballsBowled/6 );
            System.out.println("TOTAL RUNS =" + totalRuns);
            System.out.println("TOTAL BALLS =" + ballsBowled);
        }

    }

}
