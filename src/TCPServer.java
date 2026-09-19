import java.io.*;
import java.net.*;
import java.util.Scanner;

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
        Scanner keyboard = new Scanner(System.in);
        String messageBody = "";
        try (
                Socket clientSocket = socket;
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            // Keep reading messages as long as the client is connected
            while (true) {
                try {
                    //Prepares received data as a message object for parsing
                    Message receivedData = (Message) inFromClient.readObject();

                    //Reads back the message to the user, displaying only essential information
                    System.out.println("\nReceived message from: " + receivedData.getUserName());
                    System.out.println("Message: " + receivedData.getMessage());

                    //User Prompting for new message <for now a new message will not be received until the server sends a message back..>
                    System.out.print("Enter a message: ");
                    messageBody = keyboard.nextLine().trim();

                    //Prepares the new message to be sent back to the client
                    Message message = new Message("Jim", messageBody);
                    outToClient.writeObject(message);
                    outToClient.flush();

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