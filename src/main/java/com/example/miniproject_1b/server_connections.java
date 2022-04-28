package com.example.miniproject_1b;

import java.io.*;
import java.net.Socket;

public class server_connections extends Thread{

    Socket connection;
    DataOutputStream output;
    DataInputStream input;

    int playerNo;

    public server_connections(Socket socket,int playerNo){
        this.connection=socket;
        this.playerNo=playerNo;
    }

    @Override
    public void run() {
        try {
            output = new DataOutputStream(connection.getOutputStream());
            output.flush();
            input = new DataInputStream(connection.getInputStream());
            output.writeInt(playerNo);
            output.writeUTF(CowBull_settings.target);
            output.writeInt(CowBull_settings.difficulty);
            output.writeInt(CowBull_settings.noOfPlayers);

            while(connection.isConnected()) {
                while (input.available() == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CowBull_controller.getGuess(input.readUTF());
            }
            closeChat();
        }catch (IOException e){
            closeChat();
        }
    }

    void closeChat(){
        System.out.println("closing chat");
        try{
            if(input!=null)
                input.close();
            if(output!=null)
                output.close();
            if(connection!=null)
                connection.close();
        } catch (IOException e){
            //TODO
        }
    }
}
