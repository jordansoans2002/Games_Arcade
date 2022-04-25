package com.example.miniproject_1b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HandCricket_networking extends Thread {
    static ServerSocket server;
    static Socket connection;
    static DataOutputStream output;
    static DataInputStream input;

    static int inMsg;

    @Override
    public void run(){
        try {
            if(HandCricket.server) {
                server = new ServerSocket(5555);
                connection = new Socket();
                connection=server.accept();
                System.out.println("got connection");
            }
            else{
                connection = new Socket("localhost", 5555);
            }
            input =new DataInputStream(connection.getInputStream());
            output=new DataOutputStream(connection.getOutputStream());
            System.out.println("connection setup");

            while(connection.isConnected()) {
                while (input.available() == 0) {
                    Thread.sleep(1000);
                }
                inMsg=input.readInt();
            }
            closeChat();
        } catch (IOException | InterruptedException e) {
            closeChat();
        }
    }
    public static void send(int outMsg){
        try {
            output.writeInt(outMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeChat(){
        try {
            if (server != null)
                server.close();
            if(connection!=null)
                connection.close();
            if(input!=null)
                input.close();
            if(output!=null)
                output.close();
        }catch (IOException e){
            //todo
        }
    }

    public static int receive(){
        int inMsg=0;
        try {
            inMsg=input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inMsg;
    }
}