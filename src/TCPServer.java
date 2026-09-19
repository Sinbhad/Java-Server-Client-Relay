import java.io.*;
import java.net.*;

class TCPServer {
    void tcpServer() {
        int port = 6789;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try (
                Socket clientSocket = socket;
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            // Keep reading messages as long as the client is connected
            while (true) {
                try {
                    Message receivedData = (Message) inFromClient.readObject();

                    System.out.println("\nReceived message from: " + receivedData.getUserName());
                    System.out.println("Message: " + receivedData.getMessage());

                    // Example of sending a persistent response back
                    // Message ack = new Message("Server", "Message received!");
                    // outToClient.writeObject(ack);
                    // outToClient.flush();

                } catch (EOFException e) {
                    // This is expected when the client cleanly closes the connection
                    System.out.println("Client " + clientSocket.getInetAddress() + " disconnected.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}