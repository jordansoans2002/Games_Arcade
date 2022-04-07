package com.example.miniproject_1b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class multithread_client extends Thread{
    static Socket connection;
    static DataInputStream input;
    static DataOutputStream output;

    @Override
    public void run(){
        try {
            connection=new Socket("localhost",5555);
            output=new DataOutputStream(connection.getOutputStream());
            output.flush();
            input=new DataInputStream(connection.getInputStream());
            CowBull_controller.playerNo=input.readInt();

            while (connection.isConnected()){
                while(input.available() == 0){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        closeChat();
                    }
                }
                CowBull_controller.getGuess(input.readUTF());
            }
        } catch (IOException e) {
            closeChat();
        }
    }

    public static void send(String guess){
        try {
            output.writeUTF(guess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void closeChat(){
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
