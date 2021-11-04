package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {
    @Override
    public void init(){
        try {
            Scenes.main = new Scene(loadFxmlFile("Main.fxml"));
            Scenes.add = new Scene(loadFxmlFile("Add.fxml"));
            Scenes.edit = new Scene(loadFxmlFile("Edit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws IOException {

        Scenes.stage = stage;
        stage.setTitle("Клиент");
        stage.setScene(Scenes.main);
        stage.show();
    }

    private Parent loadFxmlFile(String nameFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource(nameFile));
        return  fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}