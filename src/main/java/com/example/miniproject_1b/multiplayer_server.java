package com.example.miniproject_1b;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class multiplayer_server extends Thread {
    static ServerSocket server;
    static Socket connection;
    static ObjectOutputStream output;
    static ObjectInputStream input;

    static void main() {
        try {
            try {
                makeConnection();
                setupStreams();
            } catch (EOFException e) {
                e.printStackTrace();
                System.out.println("exited");
            } finally {
                closeChat();
            }
        } catch (IOException e){
            //TODO
        }
    }

    static void makeConnection()throws IOException{
        server=new ServerSocket(5555);
        connection=server.accept();
        System.out.println("connection made");
    }

    static void setupStreams() throws IOException{
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        System.out.println("stream setup");
        //output.writeObject("ready");
    }

    static void send(String guessedWord) throws IOException {
        output.writeObject(guessedWord);
    }

    static String receive() throws IOException{
        String inMsg="*";
        try{
            return input.readObject().toString();
        } catch (ClassNotFoundException e){
            System.out.println(inMsg);
            //TODO
        }
        return inMsg;
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
            if(server!=null)
                server.close();
        } catch (IOException e){
            //TODO
        }
    }

}
