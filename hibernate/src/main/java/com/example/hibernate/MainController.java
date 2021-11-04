package com.example.hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainController {
    protected CarRunner carRunner = new CarRunner();

    @FXML
    private Button showBtn;

    @FXML
    private TableView<Car> showTable;

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
    private Button addWindow;

    @FXML
    private Button delBtn;

    @FXML
    private Button editWindow;

    private ObservableList<Car> carsData;
    protected static Stage MainStage;
    protected static Scene MainScene;
    protected static Car car;

    @FXML
    private void initialize() {
        carsData = FXCollections.observableArrayList();
        initColumn();

        showBtn.setOnAction(e -> {
            showData();
        });
        delBtn.setOnAction(e -> {
            delete();
        });
        addWindow.setOnAction(e -> {
            addWindow();
        });
        editWindow.setOnAction(e -> {
            editWindow();
        });
        showTable.setOnMouseClicked(e -> {
            getCar();
        });
    }

    private void delete() {
        if (car != null) {
            Optional<ButtonType> result = alertConfirmation("Удаление","Удалить строку?" );
            if (result.get() == ButtonType.OK){
                carsData.removeAll(car);
                carRunner.removeCar(car);
                showTable.setItems(carsData);
                car = null;
            }
        }else{
            alertWarning("Внимание", "Выберите строку!");
        }
    }

    private void getCar() {
        car = showTable.getSelectionModel().getSelectedItem();
    }

    private void editWindow() {
        if(car != null)
        try {
            MainScene = addWindow.getScene();
            MainStage = (Stage) MainScene.getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
            Scene sceneAdd = new Scene(fxmlLoader.load(), 720, 360);
            MainStage.setScene(sceneAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }else{
            alertWarning("Внимание", "Выберите строку!");
        }
    }

    private void addWindow() {
        try {
            MainScene = addWindow.getScene();
            MainStage = (Stage) MainScene.getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Scene sceneAdd = new Scene(fxmlLoader.load(), 720, 360);
            MainStage.setScene(sceneAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        List<Car> carList = carRunner.showCars();
        carsData.clear();
        carsData.addAll(carList);
        showTable.setItems(carsData);
        car = null;
    }

    private void initColumn() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        colPower.setCellValueFactory(new PropertyValueFactory<>("Power"));
        colMileage.setCellValueFactory(new PropertyValueFactory<>("Mileage"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
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
