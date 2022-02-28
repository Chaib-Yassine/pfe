package org.inti.Repository;


import org.inti.entites.Pointage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RepositoryRestResource
@CrossOrigin("*")
public interface PointageRepository extends ReactiveCrudRepository<Pointage,Integer> {

    public Mono<Pointage> findByTourniquet(String id);
    public Flux<Pointage> findAllByIdNotNullOrderByIdAsc(final Pageable page);

   /*   @CrossOrigin("*")
    @Query(value = "SELECT * FROM Pointage WHERE codebarre like %:codeBarre%")
    public Page<Pointage> getAll(
            String codeBarre,
          //  String porte,
            //String tourniquet,
           // String typetitreacces,
           // String autorization,
           // Date startDate,
           // Date endDate,
            Pageable pageable);*/
}
