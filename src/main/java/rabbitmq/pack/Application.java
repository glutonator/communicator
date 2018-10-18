package rabbitmq.pack;

import org.apache.log4j.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Application {

    public static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        System.out.println("test");
//        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info("Hello World");

//        logger.debug("Debug log message");
//        logger.info("Info log message");
//        logger.error("Error log message");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();

        Sender sender = ctx.getBean(Sender.class);
//        sender.send();

        Receiver receiver = ctx.getBean(Receiver.class);



//        ctx.close();

    }

}
