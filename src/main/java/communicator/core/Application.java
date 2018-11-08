package communicator.core;

import communicator.core.db.DatabaseConfig;
import communicator.core.db.Person;
import communicator.core.db.PersonRepository;
import org.apache.log4j.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        System.out.println("communicator/core");
        logger.info("Hello World");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(Config.class);
        ctx.register(DatabaseConfig.class);
        ctx.refresh();

        System.out.println("test");
        PersonRepository personRepository = ctx.getBean(PersonRepository.class);
        Person person = new Person("aaaa",88);
        personRepository.save(person);
        System.out.println(personRepository.findAll());

//        ctx.close();

    }

}
