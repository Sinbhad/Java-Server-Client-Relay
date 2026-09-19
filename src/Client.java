import java.io.*;
import java.net.*;
import java.util.Scanner;
class TCPClient {
    void tcpClient() {
        Scanner keyboard = new Scanner(System.in);
        String serverAddress = "192.168.1.57";
        int serverPort = 6789;

        // try-with-resources automatically closes the socket and streams
        try (
                Socket clientSocket = new Socket(serverAddress, serverPort);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            //Maintain connection for continuous messaging
            boolean continueMessaging = true;
            while(continueMessaging){
                Message messageObject = new Message();
                System.out.println("Connected to Server (USE '0' to end communication)");
                System.out.print("Enter a message: ");
                String messageBody = keyboard.nextLine().trim();
                if(messageBody.equals("0")){
                    continueMessaging = false;
                }else{
                    messageObject = new Message("Westley", messageBody);
                    outToServer.writeObject(messageObject);
                }
            }



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