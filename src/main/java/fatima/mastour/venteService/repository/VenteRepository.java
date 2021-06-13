package fatima.mastour.venteService.repository;

import fatima.mastour.venteService.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VenteRepository extends JpaRepository < Vente, Long > {
}
