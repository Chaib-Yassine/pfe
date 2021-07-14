package fatima.mastour.ticketservice.repository;

import fatima.mastour.ticketservice.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface TicketRepository extends JpaRepository <Ticket, Long> {
    Ticket  findByCodeBarre(String codeBare);

   // @RestResource(path = "/byNvente")
    public Page<Ticket> findAllByNventeEquals(@Param("mc")int titre, Pageable pageable);
}
