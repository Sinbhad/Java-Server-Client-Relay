import java.io.*;
import java.net.*;

class TCPClient {
    void tcpClient() {
        String serverAddress = "192.168.1.57"; // Changed from "hostname"
        int serverPort = 6789;

        // try-with-resources automatically closes the socket and streams
        try (
                Socket clientSocket = new Socket(serverAddress, serverPort);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());R
        ) {
            //Create data to send
            //Message for user  System.out.println();

            // Send to server
            outToServer.writeObject();(//object);

            // Read response
            // custom data = (CustomData) <- type cast inFromServer.readObject();
            // Format that chud System.out.println("FROM SERVER: " + modifiedSentence);

        } catch (ConnectException e) {
            System.err.println("Could not connect to server. Is the server running on port " + serverPort + "?");
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverAddress);
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}