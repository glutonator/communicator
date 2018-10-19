package rabbitmq.pack;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("queueHelloWord")
    private Queue queue;

    @Value("10")
    private int count;

    @Value("wiadomosc")
    private String message;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String tmpMessage = "Hello World! " + count + " " + message;
        this.template.convertAndSend(queue.getName(), tmpMessage);
        System.out.println(" [x] Sent '" + tmpMessage + "'");
        count++;
    }

//    @Autowired
    @Qualifier("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
