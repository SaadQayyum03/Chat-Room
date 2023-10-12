import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.name = socket.getInetAddress().getHostName();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            MessageReceiver receiver = new MessageReceiver(client);
            receiver.start();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            client.sendMessage(username);

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                if (message.equals("quit")) {
                    client.sendMessage(message);
                    break;
                }
                // Print buffered messages
                LinkedList<String> bufferedMsgs = receiver.getAndClearBufferedMessages();
                for (String msg : bufferedMsgs) {
                    System.out.println(msg);
                }

                client.sendMessage(message);
            }
            
        } catch (IOException e) {
            System.out.println("Could not connect to the server.");
        } finally {
            System.out.println("Closed connection to the server");
        }

        System.out.println("out herere");
    }
}
