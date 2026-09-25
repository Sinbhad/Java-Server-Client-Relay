import lib.Routable;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPServer<T> implements Routable<T> {
    public void tcpServer() {
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

    private void handleClient(Socket socket) {
        try {
            Socket clientSocket = socket;
            ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            outToClient.flush();
            Thread clientReceiver = getClientReceiver(clientSocket);
            clientReceiver.start();

            // Server sender loop: send messages to client whenever typed
            Scanner keyboard = new Scanner(System.in);
            while (!clientSocket.isClosed()) {
                System.out.print("> ");
                String messageBody = keyboard.nextLine().trim();
                if (!messageBody.isEmpty()) {
                    sendMessage("Jim", messageBody, outToClient);
                }
            }

        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }

    private static Thread getClientReceiver(Socket clientSocket) throws IOException {
        ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

        // Background thread: continuously reads incoming messages from this client
        Thread clientReceiver = new Thread(() -> {
            try {
                while (!clientSocket.isClosed()) {
                    Message receivedData = (Message) inFromClient.readObject();
                    //-------Add Queue here, create for loop that prints runs receiveMessage(receivedData); for all queued messages
                    System.out.println("\n[" + receivedData.getUserName() + "]: " + receivedData.getMessage());
                    System.out.print("> ");
                }
            } catch (EOFException | SocketException e) {
                System.out.println("\nClient " + clientSocket.getInetAddress() + " disconnected.");
            } catch (Exception e) {
                if (!clientSocket.isClosed()) {
                    System.err.println("\nError receiving from client: " + e.getMessage());
                }
            }
        });
        return clientReceiver;
    }

    @Override
    public void receiveMessage(T message, T messageCollection){
        Message convertedMessage = (Message) message;
        if(isNewConnection(convertedMessage)){
            //Temp message for debugging
            System.out.println("New user " + convertedMessage.getUserName() + " connected");
            //-------Create database to store username ip-addr pairs **something like userNameDB.addTo(String userName, String ipAddress)**
            ConnectionMessage conMessage = (ConnectionMessage) message;
            String ipAddr = conMessage.getIpAddr();
            System.out.println(ipAddr);
        }

        System.out.println(convertedMessage.getUserName());
        System.out.println(convertedMessage.getMessage());
    }

    @Override
    public void sendMessage(String userName, String body, ObjectOutputStream outToClient) {
        try {
            Message message = new Message(userName, body);
            outToClient.writeObject(message);
            outToClient.flush();
        } catch (IOException e) {
            System.err.println("Error sending message to client: " + e.getMessage());
        }
    }

    public boolean isNewConnection(Message message){
        return message.getMessage().equals("Hello Server :)");
    }
}