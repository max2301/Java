package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
    Client client;
    @FXML
    private TextField aField;

    @FXML
    private TextField bField;

    @FXML
    private TextField cField;

    @FXML
    private Button sendBtn;

    @FXML
    private TextArea resultField;

    @FXML
    void initialize() {
        client = new Client();
        sendBtn.setOnAction(event -> {
            sendData(aField.getText(),bField.getText(),cField.getText());
        });
    }

    private void sendData(String ...val) {
        client.Send(val);
        String str = client.getData();
        resultField.setText(str);
    }
}