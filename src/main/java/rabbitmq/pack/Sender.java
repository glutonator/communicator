package rabbitmq.pack;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("queue.HelloWord")
    private Queue queue;

    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send() {
        String message = "Hello World!";
        System.out.println(queue.getName());
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
