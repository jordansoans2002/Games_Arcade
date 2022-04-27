package com.example.miniproject_1b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HandCricket_test extends Application {

    public static void main(String []args){launch();}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(HandCricket_test.class.getResource("HandCricket.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Hand cricket test");
        //scene.getStylesheets().add(Objects.requireNonNull(HandCricket_test.class.getResource("HandCricket.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
