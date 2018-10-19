package rabbitmq.pack;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello", containerFactory = "rabbitListenerContainerFactory")
public class Receiver {
    @RabbitHandler
//    @RabbitListener(queues = "hello",containerFactory = "STATUS_LISTENER_CONTAINER_FACTORY")
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
