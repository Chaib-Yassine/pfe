package fatima.mastour.testservice;

import fatima.mastour.testservice.entities.Test;
import fatima.mastour.testservice.repository.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class TestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(TestRepository testRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Test.class);
        return args -> {
           testRepository.save(new Test(null,"Fatima", "fatima@glmail.com" ));
           testRepository.save(new Test(null, "Yassine", "yassine@gmail.com"));
           testRepository.findAll().forEach(c->{
                System.out.println(c.toString());
           });
        };
    }
}
