package fatima.mastour.invitationService.repository;

import fatima.mastour.invitationService.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InvitationRepository  extends JpaRepository < Invitation, Long> {
    Invitation  findByCodeBare(String codeBare);
}


