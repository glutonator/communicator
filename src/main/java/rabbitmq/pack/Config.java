package rabbitmq.pack;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class Config {
    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Bean(name = "queue.HelloWord")
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean(name = "queue.NotHelloWord")
    public Queue nothello() {
        return new Queue("not hello");
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(new CachingConnectionFactory("localhost"));
    }

}
