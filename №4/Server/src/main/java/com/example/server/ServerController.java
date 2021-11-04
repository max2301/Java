package com.example.server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private Server server;
    private String recText = "";
    private String resText = "";
    private String resTextFile = "";
    private String fileName = "fileData.txt";
    private Thread thread;

    @FXML
    protected TextArea recField;

    @FXML
    protected TextArea resField;

    @FXML
    public Button saveFileBtn;

    @FXML
    void initialize() {
        server = new Server();
        thread = new Thread(() -> {
            int i = 1;
            while (true) {
                server.listen();
                recText += i + ") " + server.input + '\n';
                resText += i++ + ") " + server.output;
                resTextFile += "результат функции: " + server.output;
                recField.setText(recText);
                resField.setText(resText);
            }
        });
        thread.start();
        saveFileBtn.setOnAction(e -> {
            writeFile();
        });
    }

    private void writeFile() {
        String[] recArr = recText.split("\n");
        String[] resArr = resTextFile.split("\n");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            for (int i=0; i<recArr.length;i++){
                bw.write(recArr[i]+" "+resArr[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
