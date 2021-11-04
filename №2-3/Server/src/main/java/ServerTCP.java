import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class ServerTCP {
    private static int countСlients = 0;

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2323)) {
            System.out.println("Server started");

            while (true) {
                final Socket socket = serverSocket.accept();
                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                new Thread(new Runnable() {
                    public void run() {
                        countСlients++;
                        System.out.println("Client " + countСlients + " connected");
                        try {
                            while (true) {
                                int num1 = reader.read();
                                int num2 = reader.read();

                                System.out.println("Received: " + num1 + " " + num2);
                                int result = gcd(num1, num2);

                                writer.write(result);
                                writer.flush();
                            }
                        } catch (IOException e) {
                           
                        } finally {
                            try {
                                countСlients--;
                                System.out.println("Client disconnected");
                                System.out.println("Number of connected clients: " + countСlients);
                                writer.close();
                                reader.close();
                                socket.close();
                            } catch (IOException ex) {
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
