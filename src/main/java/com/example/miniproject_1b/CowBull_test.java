package com.example.miniproject_1b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CowBull_test extends Application {

    public static void main(String []args){launch();}
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(CowBull_test.class.getResource("Cow_Bull.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),270,380);
        stage.setTitle("CowBull_tester");
        stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(CowBull_test.class.getResource("CowBull.css")).toExternalForm());
        stage.show();
    }
}
