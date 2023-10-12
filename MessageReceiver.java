import java.io.IOException;
import java.util.LinkedList;

public class MessageReceiver extends Thread {
    private final Client client;
    private final Object lock = new Object();
    private LinkedList<String> bufferedMessages = new LinkedList<>();

    public MessageReceiver(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String receivedMessage;
            while ((receivedMessage = client.receiveMessage()) != null) {
                String[] temp = receivedMessage.split(":");
                if (!temp[1].equals(" ")){
                    if (bufferedMessages.size() == 10) {
                        bufferedMessages.removeFirst();
                    }
                  bufferedMessages.add(receivedMessage);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading message from server.");
        }
    }

    public LinkedList<String> getAndClearBufferedMessages() {
        LinkedList<String> tmp = new LinkedList<>(bufferedMessages);
        bufferedMessages.clear();
        return tmp;
    }
}
