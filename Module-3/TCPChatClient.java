import java.io.*;
import java.net.*;

public class TCPChatClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // or server IP
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to the server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Thread to listen to server messages
            Thread receiveThread = new Thread(() -> {
                try {
                    String msgFromServer;
                    while ((msgFromServer = in.readLine()) != null) {
                        System.out.println("Server: " + msgFromServer);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            });

            receiveThread.start();

            // Main thread: read from console and send to server
            String msgToServer;
            while ((msgToServer = consoleReader.readLine()) != null) {
                out.write(msgToServer);
                out.newLine();
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
