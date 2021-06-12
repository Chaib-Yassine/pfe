package fatima.mastour.invitationService;

import fatima.mastour.invitationService.entities.Invitation;
import fatima.mastour.invitationService.repository.InvitationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvitationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(InvitationServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(InvitationRepository invitationRepository){
        return args -> {
            invitationRepository.save(new Invitation (null, 123, "Organisateur",true));
            invitationRepository.save(new Invitation(null, 1234, "Exposant",true));
            invitationRepository.save(new Invitation(null, 12345, "Press",false));
            invitationRepository.save(new Invitation(null, 123456, "Press",false));
            invitationRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
        }
    }

