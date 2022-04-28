package com.example.miniproject_1b;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class multithread_server extends Thread{

    static ServerSocket server;
    static Socket connection;
    static server_connections[] connections=new server_connections[CowBull_settings.noOfPlayers-1];
    int playerNo=0;

    public void run(){
        try {
            server = new ServerSocket(5555);
            while(playerNo<CowBull_settings.noOfPlayers-1) {
                connection = server.accept();
                connections[playerNo]=new server_connections(connection,playerNo+1);
                connections[playerNo].start();
                System.out.println((playerNo+1)+" set up");
                playerNo++;
            }
        }catch (IOException e){
            //todo
        }
    }

    public static void send(String guess){
        for(int i=0;i<CowBull_settings.noOfPlayers-1;i++){
            try {
                if(i==CowBull_controller.chance-1 && guess.length()!=1)
                    continue;
                try{
                    connections[i].output.writeUTF(guess);}
                catch (NullPointerException e){
                    System.out.println("Player "+i+" has disconnected");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeChat(){
        try {
            for (int i = 0; i < CowBull_settings.noOfPlayers - 1; i++) {
                if(connections[i]!=null)
                    connections[i].closeChat();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            //todo;
        }
    }
}
