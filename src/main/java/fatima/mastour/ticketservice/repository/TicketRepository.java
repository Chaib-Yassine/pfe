package fatima.mastour.ticketservice.repository;

import fatima.mastour.ticketservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends JpaRepository <Ticket, Long> {
    Ticket  findByCodeBarre(String codeBare);
}
