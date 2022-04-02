package com.example.miniproject_1b;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class multiplayer_client {

    static String msgs;
    static Socket connection;
    static ObjectOutputStream output;
    static ObjectInputStream input;

    static void main(){
        try {
            try {
                makeConnection();
                setupStreams();
                msgs="ready to start";
                System.out.println(msgs);
                while(!msgs.equals("END")){
                    recieve();
                    //send();
                    //recieve();
                }
            } catch (EOFException e) {
                e.printStackTrace();
                System.out.println("Connection closed");
            } finally {
                closeChat();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void makeConnection()throws IOException{
        connection=new Socket("127.0.0.1",5555);
    }

    static void setupStreams() throws IOException{
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input=new ObjectInputStream(connection.getInputStream());
    }

    static void send()throws IOException{
        System.out.print("guess to send: ");
        Scanner sc=new Scanner(System.in);
        msgs=sc.nextLine();
        output.writeObject(msgs);

    }
    static void recieve()throws IOException {
        try {
            msgs=input.readObject().toString();
            if(msgs.equals("1"))
                send();
            System.out.println(msgs);
        } catch (ClassNotFoundException e){
            //todo
        }
    }

    static void closeChat(){
        try {
            if (input != null)
                input.close();
            if (output != null)
                output.close();
            if (connection != null)
                connection.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}