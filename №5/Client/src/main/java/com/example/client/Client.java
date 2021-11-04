package com.example.client;

import com.examle.server.Car;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static Client instance;
    public static String host;
    public static int port;
    public static Car car;

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    private Client() {
        try {
            this.socket = new Socket("localhost", 2025);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void sendData(String ex, Car car) {
        try {
            out.writeObject(ex);
            out.writeObject(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(Car car) {
        try {
            out.writeObject("delete");
            out.writeObject(String.valueOf(car.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Car> receiveObject() {
        try {
            out.writeObject("show");
            ArrayList<Car> resList = (ArrayList<Car>) in.readObject();

            return resList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
