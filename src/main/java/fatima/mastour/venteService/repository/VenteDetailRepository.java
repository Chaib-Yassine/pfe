package fatima.mastour.venteService.repository;

import fatima.mastour.venteService.entities.VenteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin("*")
public interface VenteDetailRepository extends JpaRepository <VenteDetail, Long> {
    public Collection<VenteDetail> findByVenteId(Long id);
}
