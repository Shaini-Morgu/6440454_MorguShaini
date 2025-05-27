import java.io.*;
import java.net.*;

public class TCPChatServer {
    public static void main(String[] args) {
        int port = 12345; // Server port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Streams to send and receive data
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // For reading from server console input
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Thread to listen to client messages
            Thread receiveThread = new Thread(() -> {
                try {
                    String msgFromClient;
                    while ((msgFromClient = in.readLine()) != null) {
                        System.out.println("Client: " + msgFromClient);
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected.");
                }
            });

            receiveThread.start();

            // Main thread: read from console and send to client
            String msgToClient;
            while ((msgToClient = consoleReader.readLine()) != null) {
                out.write(msgToClient);
                out.newLine();
                out.flush();
            }

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
