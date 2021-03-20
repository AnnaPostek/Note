package pl.postek.webservice.notes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class NoteApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
