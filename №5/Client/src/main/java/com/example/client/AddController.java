package com.example.client;

import com.examle.server.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;


public class AddController {

    private Client client;
    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField powerField;

    @FXML
    private TextField mileageField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField colorField;

    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;

    @FXML
    private void initialize() {
        client = Client.getInstance();
        backBtn.setOnAction(e -> {
            back();
        });
        addBtn.setOnAction(e -> {
            addData();
        });
    }

    private void back() {
        Scenes.stage.setScene(Scenes.main);
    }

    private void addData() {
        if (!brandField.getText().equals("") && !modelField.getText().equals("")
                && !powerField.getText().equals("") && !mileageField.getText().equals("")
                && !yearField.getText().equals("")){

            Car car = new Car(brandField.getText(), modelField.getText(),
                    Integer.parseInt(powerField.getText()), Double.parseDouble(mileageField.getText()),
                    Integer.parseInt(yearField.getText()), colorField.getText());

            client.sendData("add", car);
        }else {
            alertWarning("Внимание", "Заполните все поля!");
        }
    }

    private void alertWarning(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
