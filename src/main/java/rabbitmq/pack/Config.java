package rabbitmq.pack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Sender sender() {
        return new Sender();
    }

}
