package com.example.hibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AddController extends MainController {

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private Button addBtn;

    @FXML
    private TextField powerField;

    @FXML
    private TextField mileageField;

    @FXML
    private TextField yearField;

    @FXML
    private Button backBtn;

    @FXML
    private void initialize() {
        backBtn.setOnAction(e -> {
            back();
        });
        addBtn.setOnAction(e -> {
            addData();
        });
    }

    private void addData() {
        if (!brandField.getText().equals("") && !modelField.getText().equals("")
                && !powerField.getText().equals("") && !mileageField.getText().equals("")
                && !yearField.getText().equals("")) {

            Car car = new Car(brandField.getText(), modelField.getText(),
                    Integer.parseInt(powerField.getText()), Double.parseDouble(mileageField.getText()),
                    Integer.parseInt(yearField.getText()));

            carRunner.addCar(car);
        } else {
            alertWarning("Внимание","Заполните все поля!");
        }
    }

    private void alertWarning(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void back() {
        MainController.MainStage.setScene(MainController.MainScene);
    }
}

