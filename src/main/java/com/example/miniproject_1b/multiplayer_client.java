package com.example.miniproject_1b;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class multiplayer_client {

    static Socket connection;
    static ObjectOutputStream output;
    static ObjectInputStream input;

    static void main(){
        try {
            try {
                makeConnection();
                setupStreams();
                receive();
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
        System.out.println("connection made");
    }

    static void setupStreams() throws IOException{
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input=new ObjectInputStream(connection.getInputStream());
        System.out.println("streams setup");
        /*try {
            System.out.println(input.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    static void receive() {
        String inMsg="*";
        try {
            inMsg=input.readObject().toString();
            if(inMsg.equals("1")) {
                CowBull_multiplayer.enterGuesses.setEditable(true);
                return;
            }
            System.out.println(inMsg);
        } catch (ClassNotFoundException | IOException e){
            //todo
        }
    }
    static void send(String guessWord){
        try {
            output.writeObject(guessWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        receive();

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