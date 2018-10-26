package rabbitmq.pack;

public class Rec {

    public void handleMessage(String message) {
        System.out.println("received: " + message);
    }
}
