import java.net.*;
import java.io.*;
import java.util.*;


public class Server {
    private static int port = 12345;
    private static Set<ClientHandler> clients = new HashSet<>();
    private static int count = 0;
    public static void main(String[] args) {
        try {
            // create server sockets
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Chat server has started. Listening on " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, count);
                clients.add(clientHandler);
                count++;
                clientHandler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void broadcastAll(String message, ClientHandler current) {
        for (ClientHandler client : clients){
            if (client != current) {
                client.sendMessage(message);
            }
        }
    }
    
}