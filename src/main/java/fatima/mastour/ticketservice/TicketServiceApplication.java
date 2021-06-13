package fatima.mastour.ticketservice;

import fatima.mastour.ticketservice.entities.Ticket;
import fatima.mastour.ticketservice.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class TicketServiceApplication {

    public static void main(String[] args) {

       SpringApplication.run(TicketServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(TicketRepository ticketRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Ticket.class);
        return args ->{
             ticketRepository.save(new Ticket(null, 1, "012", true, 12,null));
             ticketRepository.save(new Ticket(null, 1234, "0123", true,13,null));
             ticketRepository.save(new Ticket(null, 12345,"01234", true,14,null ));
             ticketRepository.save(new Ticket(null, 123456,"012345", false,15,null));
             ticketRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
             });
        };
        }
    }

