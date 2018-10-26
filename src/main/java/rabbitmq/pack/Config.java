package rabbitmq.pack;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
// @EnableRabbit umożliwiło używanie Listenerów RabbitMQ !!!!
@EnableRabbit
@PropertySource(value = "classpath:/app.properties",ignoreResourceNotFound = true)
public class Config {

    //region RabbitMQ Configuration
    /**
     * @return Rabbit-admin bean that allow creating queues, exchanges, etc.
     */
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    @Bean(name = "connectionFactory")
    public AbstractConnectionFactory connectionFactory() {
        AbstractConnectionFactory factory = new CachingConnectionFactory();
        //todo: przenieść konfigurację do pom albo innego zewnętrzengo pliku
        factory.setAddresses("localhost");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Application.logger.info("CachingConnectionFactory created");

        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(AbstractConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }


    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);

        return factory;
    }
    //endregion


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean(name = "queueHelloWord")
    public Queue hello() {
        //durable - czy jest stała
        //exclusive - czy jest dla jedneog konsumenta - jak konsument znika to znika kolejka
        //autoDelete - jeśli liczba konsumentów spada do zera to kolja jest usuwana, w przypadku utraty połaaczenia nie zostaje usuwana
        return new Queue("hello", true, false, true);
    }

    @Bean(name = "queueNotHelloWord")
    public Queue nothello() {
        return new Queue("not hello", true, false, true);
    }


    @Bean
    public String string() {
        return "qqqqqq";
    }

    @Bean(name = "message")
    public String string2() {
        return "wwwwwww";
    }
}
