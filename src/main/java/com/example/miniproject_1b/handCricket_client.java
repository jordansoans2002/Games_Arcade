package com.example.miniproject_1b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class handCricket_client {

    static Socket connection;
    static DataOutputStream output;
    static DataInputStream input;

    public static void main(){
        try {
            connection = new Socket("8.1.6.33", 5555);
            output = new DataOutputStream(connection.getOutputStream());
            input = new DataInputStream(connection.getInputStream());
            System.out.println("connection setup");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int receive(){
        int r=0;
        try {
            r=input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
    public static void send(int ball){
        try {
            output.writeInt(ball);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}