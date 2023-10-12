import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String name;
    private String address;
    public int id;

    private String exitMessage = "quit";

    public ClientHandler(Socket socket, int id) throws IOException {
        this.socket = socket;

        // This is the server's output stream. When the server writes to this, 
        // it sends data to the client. The client reads from its corresponding 
        // input stream to receive this data.
        this.out = new PrintWriter(this.socket.getOutputStream(), true); 
        
        // This is the server's input stream. The server reads from this to get 
        // data sent by the client. When the client sends data, it writes to its 
        // own output stream, which is read by this input stream on the server side.
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.name = receiveMessage();
        this.address = this.socket.getInetAddress().getHostAddress();
        this.id = id;
        System.out.println(this.id + " has connected.");
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() throws IOException{
        return in.readLine();
    }
    
    public void run() {

        try {
            String message;
            while (!(message = receiveMessage()).equals(exitMessage)) {
                message = this.name + ": " + message;
                System.out.println(message);
                Server.broadcastAll(message, this);
            }
            
        } catch (IOException e) {
            System.out.println("Error receving messages from " + this.id + ".");
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Could not close " + this.name + "connection!!!");
            }
        }

    }
}
