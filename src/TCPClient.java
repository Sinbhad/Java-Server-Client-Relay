import java.io.*;
import java.net.*;
import java.util.Scanner;

import lib.Routable;

class TCPClient<T> implements Runnable, Routable<T>{
    public void tcpClient() {
        Scanner keyboard = new Scanner(System.in);
        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String clientAddress = localhost.toString();
        String serverAddress = "192.168.1.57";
        int serverPort = 6789;
        MessageCollection messageCollection = new MessageCollection();

        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);

            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.flush();
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Enter a username");
            System.out.print(">");
            String userName = keyboard.nextLine();
            sendConnectionMessage(userName, "Hello Server :)", clientAddress, outToServer);
            System.out.println("Connected to Server (Type '0' to exit)");

            Thread receiverThread = new Thread(() -> {
                try {
                    while (!clientSocket.isClosed()) {
                        Message receivedData = (Message) inFromServer.readObject();

                        synchronized (messageCollection) {
                            //noinspection unchecked
                            receiveMessage((T) receivedData, (T) messageCollection);
                        }

                        System.out.println("\n[Server]: " + receivedData.getMessage());
                        System.out.print("> "); // Keep the input prompt visible
                    }
                } catch (EOFException | SocketException e) {
                    System.out.println("\nDisconnected from server.");
                } catch (Exception e) {
                    if (!clientSocket.isClosed()) {
                        System.err.println("\nReceive error: " + e.getMessage());
                    }
                }
            });
            receiverThread.start();

            while (true) {
                System.out.print("> ");
                String messageBody = keyboard.nextLine().trim();

                if (messageBody.equals("0")) {
                    System.out.println("Closing connection...");
                    clientSocket.close(); // Triggers SocketException in receiverThread to stop it
                    break;
                }

                if (!messageBody.isEmpty()) {
                    sendMessage(userName, messageBody, outToServer);
                }
            }

            receiverThread.join();

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
        }
    }

    @Override
    public void receiveMessage(T message, T messageCollection){
        Message convertedMessage = (Message) message;
        MessageCollection convertedCollection = (MessageCollection) messageCollection;
        convertedCollection.addByUserName(convertedMessage);
    }

    @Override
    public void sendMessage(String userName, String body, ObjectOutputStream outToServer) throws IOException {
        Message message = new Message(userName, body);
        outToServer.writeObject(message);
        outToServer.flush();
     }

     public void sendConnectionMessage(String userName, String body, String ipAddr, ObjectOutputStream outToServer) throws IOException {
        ConnectionMessage conMess = new ConnectionMessage(userName, body, ipAddr);
        outToServer.writeObject(conMess);
        outToServer.flush();
     }

    @Override
    public void run() {

    }
}