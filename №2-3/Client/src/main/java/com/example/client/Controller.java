package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;

public class Controller {
    private ClientTCP client = null;

    @FXML
    private Button connect_btn;

    @FXML
    private TextField ip_field;

    @FXML
    private TextField port_field;

    @FXML
    private Button send_btn;

    @FXML
    private TextField num_field1;

    @FXML
    private TextField num_field2;

    @FXML
    private TextArea result_text;

    @FXML
    private void initialize() {
        ip_field.setText("127.0.0.1");
        port_field.setText("2323");
        connect_btn.setOnAction(event -> {
            connect();
        });
        send_btn.setOnAction(event -> {
            send();
        });
    }

    private void send() {
        if(client != null){
            client.send(Integer.parseInt(num_field1.getText()),Integer.parseInt(num_field2.getText()));
            String result = client.read();
            result_text.setText(result);
        }else{
            result_text.setText("no connection");
        }

    }

    private void connect() {
        if(client == null)
        try {
            Socket socket = new Socket(ip_field.getText(),Integer.parseInt(port_field.getText()));
            client = new ClientTCP(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
