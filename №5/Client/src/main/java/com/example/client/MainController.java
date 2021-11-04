package com.example.client;

import com.examle.server.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainController {

    private Client client;

    @FXML
    private TableView<Car> showTable;

    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;

    @FXML
    private Button connectBtn;

    @FXML
    private Button showBtn;

    @FXML
    private Button delBtn;

    @FXML
    private Button addWindow;

    @FXML
    private Button editWindow;

    @FXML
    private TableColumn<Car, Integer> colId;

    @FXML
    private TableColumn<Car, String> colBrand;

    @FXML
    private TableColumn<Car, String> colModel;

    @FXML
    private TableColumn<Car, Integer> colPower;

    @FXML
    private TableColumn<Car, Double> colMileage;

    @FXML
    private TableColumn<Car, Integer> colYear;

    @FXML
    private TableColumn<Car, String> colColor;

    private ObservableList<Car> CarsData;

    @FXML
    private void initialize() {
        CarsData = FXCollections.observableArrayList();
        hostField.setText("localhost");
        portField.setText("2025");

        initColumn();

        connectBtn.setOnAction(e -> {
            connect();
        });
        showBtn.setOnAction(e -> {
            show();
        });
        addWindow.setOnAction(e -> {
            addWindow();
        });
        showTable.setOnMouseClicked(e -> {
            getCar();
        });
        delBtn.setOnAction(e -> {
            delete();
        });
        editWindow.setOnAction(e -> {
            editWindow();
        });
    }

    private void editWindow() {
        if (client == null) {
            alertWarning("Внимание", "Подключитесь к серверу!");
        } else if (Client.car != null) {
            Scenes.stage.setScene(Scenes.edit);
        } else {
            alertWarning("Редактирование", "Выберите строку!");
        }
    }

    private void delete() {
        if (client == null) {
            alertWarning("Внимание", "Подключитесь к серверу!");
        } else if (Client.car != null) {

            Optional<ButtonType> result = alertConfirmation("Удаление", "Удалить строку?");
            if (result.get() == ButtonType.OK) {
                CarsData.removeAll(Client.car);
                client.deleteData(Client.car);
                showTable.setItems(CarsData);
                Client.car = null;
            }
        } else {
            alertWarning("Удаление", "Выберите строку!");
        }
    }

    private void getCar() {
        Client.car = showTable.getSelectionModel().getSelectedItem();
    }

    private void addWindow() {
        if (client != null) {
            Scenes.stage.setScene(Scenes.add);
        } else {
            alertWarning("Внимание", "Подключитесь к серверу!");
        }
    }

    private void initColumn() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        colPower.setCellValueFactory(new PropertyValueFactory<>("Power"));
        colMileage.setCellValueFactory(new PropertyValueFactory<>("Mileage"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
    }

    private void initData(ArrayList<Car> arrayList) {
        CarsData.clear();
        CarsData.addAll(arrayList);
    }

    private void show() {
        if (client != null) {
            ArrayList<Car> arrayList = client.receiveObject();
            initData(arrayList);
            showTable.setItems(CarsData);
            Client.car = null;
        } else {
            alertWarning("Внимание", "Подключитесь к серверу!");
        }
    }

    private void connect() {
        if (client == null) {
            Client.host = hostField.getText();
            Client.port = Integer.parseInt(portField.getText());
            client = Client.getInstance();

            alertInfo("Подключение", "Связь с сервером установлена!");
        }
    }

    private void alertInfo(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
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