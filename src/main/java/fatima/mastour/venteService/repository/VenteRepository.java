package fatima.mastour.venteService.repository;

import fatima.mastour.venteService.entities.Vente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@RepositoryRestResource
@CrossOrigin("*")
public interface VenteRepository extends JpaRepository < Vente, Long > {
    @RestResource(path = "/byDates")
    @Query(value = "SELECT * FROM Vente WHERE ventedate >= :startDate AND ventedate <= :endDate", nativeQuery = true)
    public Page<Vente> getAllBetweenDates(@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                           @Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Pageable pageable);

}
