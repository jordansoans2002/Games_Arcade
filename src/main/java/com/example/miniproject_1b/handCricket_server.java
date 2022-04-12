package com.example.miniproject_1b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class handCricket_server{

    static ServerSocket server;
    static Socket connection;
    static DataOutputStream output;
    static DataInputStream input;
    public static void main(){
        try {
            server=new ServerSocket(5555);
            connection=new Socket();
            connection=server.accept();
            input =new DataInputStream(connection.getInputStream());
            output=new DataOutputStream(connection.getOutputStream());
            System.out.println("connection setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int receive(){
        int b=0;
        try {
            b=input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
    public static void send(int runs){
        try {
            output.writeInt(runs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}