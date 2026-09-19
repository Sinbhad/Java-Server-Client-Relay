import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
    void tcpClient() {
        Scanner keyboard = new Scanner(System.in);
        String serverAddress = "192.168.1.57";
        int serverPort = 6789;

        try (
                Socket clientSocket = new Socket(serverAddress, serverPort);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            System.out.println("Connected to Server (USE '0' to end communication)");
            boolean continueMessaging = true;

            while(continueMessaging) {
                System.out.print("\nEnter a message: ");
                String messageBody = keyboard.nextLine().trim();

                if (messageBody.equals("0")) {
                    continueMessaging = false;
                    System.out.println("Closing connection...");
                } else {
                    Message messageObject = new Message("Westley", messageBody);
                    outToServer.writeObject(messageObject);
                    outToServer.flush();


                    try {
                        Message receivedData = (Message) inFromServer.readObject();
                        System.out.println("\nReceived message from: " + receivedData.getUserName());
                        System.out.println("Message: " + receivedData.getMessage());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        } catch (ConnectException e) {
            System.err.println("Could not connect to server. Is it running on port " + serverPort + "?");
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverAddress);
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}