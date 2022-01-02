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

            invitationRepository.save(new Invitation(null, "IN14322345", "Press".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2R123456", "Press".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "IN1432d", "Press".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2R12e56", "Press".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "IN1e2345", "media".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2d123456", "media".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "IN1432f345", "media".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2Rf23456", "media".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "INf322345", "invitation".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2R1v3456", "invitation".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "IN14v322345", "invitation".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2Rc23456", "invitation".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "INf32234z5", "invitation".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2R1v34z56", "invitation".toUpperCase(),true));
            invitationRepository.save(new Invitation(null, "IN14v322z345", "invitation".toUpperCase(),false));
            invitationRepository.save(new Invitation(null, "IN2Rc23z456", "invitation".toUpperCase(),true));
            invitationRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
        }
    }

