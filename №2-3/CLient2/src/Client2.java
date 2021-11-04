import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Connected to server");
            while (true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input num1: ");
                int num1 = scanner.nextInt();
                System.out.println("Input num2: ");
                int num2 = scanner.nextInt();

                writer.write(num1);
                writer.write(num2);

                writer.flush();

                int response = reader.read();
                System.out.println("Response: " + response);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
