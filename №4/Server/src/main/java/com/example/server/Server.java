package com.example.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    private final static int SERVER_PORT = 2525;
    private double sum1 = 0.0;
    private double sum2 = 0.0;
    private DatagramSocket serverSocket;
    private byte[] DataBuffer = new byte[100];
    private DatagramPacket datagramPacket = new DatagramPacket(DataBuffer, DataBuffer.length);
    public String input = "";
    public String output = "";

    Server() {
        try {
            serverSocket = new DatagramSocket(SERVER_PORT);
            System.out.println("Сервер запущен...");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            String[] arr = receivingData();

            final int a, b, c;
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[1]);
            c = Integer.parseInt(arr[2]);
            input = "a="+a+" b="+b+" c="+c;

            Thread t1 = new Thread(() -> {
                for (double n = a; n <= b; n++) {
                    sum1 = sum1 + Math.pow(n, n);
                }
            });
            Thread t2 = new Thread(() -> {
                for (double n = b; n <= c; n++) {
                    sum2 = sum2 + 2.0 * n / (n + 1.0);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            sendData();
        } catch (SocketException e) {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String[] receivingData() throws IOException {
        serverSocket.receive(datagramPacket);

        String receivedData = new String(datagramPacket.getData());

        receivedData = receivedData.substring(0, receivedData.indexOf('\n'));

        return receivedData.split(" ");
    }

    private void sendData() throws IOException {
        String sendData = String.valueOf(sum1 - sum2) + '\n';
        sum1 = 0;
        sum2 = 0;
        output = sendData;

        DataBuffer = sendData.getBytes();

        InetAddress senderAddress = datagramPacket.getAddress();
        int senderPort = datagramPacket.getPort();

        DatagramPacket outputPacket = new DatagramPacket(
                DataBuffer, DataBuffer.length,
                senderAddress, senderPort
        );

        serverSocket.send(outputPacket);
    }
    public void close(){
        serverSocket.close();
    }
}
