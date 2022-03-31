package com.example.miniproject_1b;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class multiplayer_client {

    Socket connection;
    ObjectOutputStream output;
    ObjectInputStream input;

    void main(){
        try {
            try {
                makeConnection();
                setupStreams();
            } catch (EOFException e) {
                //todo
            } finally {
                closeChat();
            }
        } catch (IOException e){
            //TODO
        }
    }

    void makeConnection() throws IOException{
        connection=new Socket("127.0.0.1",5555);
    }
    void setupStreams() throws IOException{
        output =new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
    }

    void closeChat(){
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
