package rabbitmq.pack;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
//@PropertySource("classpath:/applicationprop.properties")
@PropertySource(value = "classpath:/applicationprop.properties",ignoreResourceNotFound = true)
public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("queueHelloWord")
    private Queue queue;

//    @Value("10")
//    @Value("#{applicationprop.aaaaa }")
    @Value("#{'${aaaaa} '}")
    private int count;

    @Value("wiadomosc")
//    @Value("${zmienna.test}")
//    @Value("#{applicationprop['zmienna'] }")
    private String message;

    private List<String> messageList;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String tmpMessage = "Hello World! " + count + " " + message;
        this.template.convertAndSend(queue.getName(), tmpMessage);
        System.out.println(" [x] Sent '" + tmpMessage + "'");
        count++;
    }

    public void send222() {
        for(String message : messageList) {
            this.template.convertAndSend(queue.getName(), message);
        }

    }

//    @Autowired
    @Qualifier("message")
    public void setMessage(String message) {
        this.message = message;
    }



}
