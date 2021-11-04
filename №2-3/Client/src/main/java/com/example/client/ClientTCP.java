package com.example.client;

import java.io.*;
import java.net.Socket;

public class ClientTCP {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public ClientTCP(Socket socket) {
        try {
            this.socket = socket;
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(int num1, int num2) {
        try {
            writer.write(num1);
            writer.write(num2);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
            int response = reader.read();
            return String.valueOf(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
