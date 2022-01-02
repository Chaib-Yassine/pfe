package fatima.mastour.invitationService.repository;

import fatima.mastour.invitationService.entities.Invitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface InvitationRepository  extends JpaRepository < Invitation, Long> {
    Invitation  findByCodeBare(String codeBare);
    boolean existsByType(String type);
    @RestResource(path = "/byCodeBarre")
    public Page<Invitation> findInvitationByCodeBareContains(@Param("mc")String titre, Pageable pageable);

}


