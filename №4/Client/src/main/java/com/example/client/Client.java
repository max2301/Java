package com.example.client;

import java.io.IOException;
import java.net.*;

public class Client {
    private final static int SERVICE_PORT = 2525;
    private DatagramSocket clientSocket;
    private InetAddress IPAddress;

    Client(){
        try {
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void Send(String ...val){
        String str="";
        for (String num: val) {
            str+=num+" ";
        }
        str=str.trim()+'\n';

        byte[] DataBuffer = str.getBytes();

        DatagramPacket sendingPacket = new DatagramPacket(DataBuffer, DataBuffer.length, IPAddress, SERVICE_PORT);
        try {
            clientSocket.send(sendingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(){
        byte[] DataBuffer = new byte[256];
        DatagramPacket receivingPacket = new DatagramPacket(DataBuffer, DataBuffer.length);
        try {
            clientSocket.receive(receivingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String receivedData = new String(receivingPacket.getData());
        receivedData = receivedData.substring(0, receivedData.indexOf('\n'));
        return  receivedData;
    }
}