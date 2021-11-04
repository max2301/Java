package com.examle.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    private int PORT = 2025;
    private ServerSocket serverSocket;
    private Connection connection;


    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен...");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autodb", "root","root");

            listen();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Thread thread = new Thread(() -> {
                    System.out.println("Клиент подключен!");
                    try {

                        while (true) {

                            String recStr = (String) in.readObject();
                            System.out.println("Пришло: " + recStr);

                            Statement statement = connection.createStatement();

                            if (recStr.equals("show")) {
                                showData(out, statement);
                            } else if (recStr.equals("add")) {
                                addData(in);
                            } else if (recStr.equals("delete")) {
                                deleteData(in);
                            } else if (recStr.equals("edit")) {
                                editData(in);
                            }
                        }
                    } catch (IOException | SQLException | ClassNotFoundException e) {
                    } finally {
                        try {
                            in.close();
                            out.close();
                            socket.close();
                            System.out.println("Клиент отключен!");
                        } catch (IOException e) {
                        }
                    }
                });

                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void showData(ObjectOutput out, Statement statement) throws SQLException, IOException {
        ResultSet resultSet = statement.executeQuery("SELECT* FROM cars;");

        ArrayList<Car> arrayList = new ArrayList<>();

        while (resultSet.next()) {
            arrayList.add(new Car(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getDouble(5), resultSet.getInt(6),
                    resultSet.getString(7)));
        }

        out.writeObject(arrayList);
    }

    private void addData(ObjectInput in) {
        try {
            Car car = (Car) in.readObject();

            String sql = "INSERT INTO cars (brand, model,power,mileage, year, color) Values (?, ?, ?, ?, ?, ?)";

            PreparedStatement prSt = connection.prepareStatement(sql);
            prSt.setString(1, car.getBrand());
            prSt.setString(2, car.getModel());
            prSt.setInt(3, car.getPower());
            prSt.setDouble(4, car.getMileage());
            prSt.setInt(5, car.getYear());
            prSt.setString(6, car.getColor());
            prSt.executeUpdate();

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteData(ObjectInput in) {
        try {
            String recId = (String) in.readObject();

            String sql = "DELETE FROM cars WHERE id=?;";
            PreparedStatement prSt = connection.prepareStatement(sql);
            prSt.setInt(1, Integer.parseInt(recId));
            prSt.executeUpdate();

        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void editData(ObjectInput in) {
        try {
            Car car = (Car) in.readObject();

            String sql = "UPDATE cars SET brand=?,model=?,power=?,mileage=?,year=?,color=? WHERE id=?";

            PreparedStatement prSt = connection.prepareStatement(sql);
            prSt.setString(1, car.getBrand());
            prSt.setString(2, car.getModel());
            prSt.setInt(3, car.getPower());
            prSt.setDouble(4, car.getMileage());
            prSt.setInt(5, car.getYear());
            prSt.setString(6, car.getColor());
            prSt.setInt(7, car.getId());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

