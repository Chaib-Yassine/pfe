package fatima.mastour.venteService.repository;

import fatima.mastour.venteService.entities.VenteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface VenteDetailRepository extends JpaRepository <VenteDetail, Long> {
    public Collection<VenteDetail> findByVenteId(Long id);
}
