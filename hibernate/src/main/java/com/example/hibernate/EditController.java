package com.example.hibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class EditController extends MainController {

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private Button editBtn;

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
        initField();

        backBtn.setOnAction(e -> {
            back();
        });
        editBtn.setOnAction(e -> {
            editData();
        });
    }

    private void editData() {
        if (!brandField.getText().equals("") && !modelField.getText().equals("")
                && !powerField.getText().equals("") && !mileageField.getText().equals("")
                && !yearField.getText().equals("")) {

            Optional<ButtonType> result = alertConfirmation("Редактирование","Изменить данные?");
            if (result.get() == ButtonType.OK) {

                Car car = new Car(MainController.car.getId(), brandField.getText(), modelField.getText(),
                        Integer.parseInt(powerField.getText()), Double.parseDouble(mileageField.getText()),
                        Integer.parseInt(yearField.getText()));
                carRunner.updateCar(car);
            }
        } else {
            alertWarning("Внимание","Заполните все поля!");
        }
    }

    private void back() {
        MainController.MainStage.setScene(MainController.MainScene);
    }

    private void initField() {
        Car car = MainController.car;
        brandField.setText(car.getBrand());
        modelField.setText(car.getModel());
        powerField.setText(String.valueOf(car.getPower()));
        mileageField.setText(String.valueOf(car.getMileage()));
        yearField.setText(String.valueOf(car.getYear()));
    }

    private void alertWarning(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private Optional<ButtonType> alertConfirmation(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

        return alert.showAndWait();
    }
}
