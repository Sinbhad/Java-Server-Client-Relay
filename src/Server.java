import java.io.*;
import java.net.*;

class TCPServer {
    void tcpServer() {
        int port = 6789;

        // try-with-resources automatically closes the ServerSocket if the server stops
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Infinite loop to keep accepting new client connections
            while (true) {
                // accept() blocks until a new client connects
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());

                // Hand the client off to a new thread so the main thread can go back
                // to accepting more clients immediately.
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
    private static void handleClient(Socket socket) {
        try (
                Socket clientSocket = socket;
                // OUT must be created before IN
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            // Read the object from the client
            // ******CustomData receivedData = (CustomData) inFromClient.readObject();
            System.out.println("Received: " + receivedData);

            // Modify the object data
            receivedData.message = receivedData.message.toUpperCase();

            // Send the exact same object back
            outToClient.writeObject(receivedData);

        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}

