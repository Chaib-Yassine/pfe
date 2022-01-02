package com.gb.gb.repository;

import com.gb.gb.entities.Badge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    Badge  findByCodebare(String codeBare);
    boolean existsByCin(String cin);

    @CrossOrigin("*")
    @Query(value = "SELECT * FROM Badge WHERE codebare like %:codeBarre% and nom like %:nom% and prenom like %:prenom% and cin like %:cin% and organisme like %:organisme% and groupe like %:groupe% and typebadgeid like %:acces%"  , nativeQuery = true)
    public Page<Badge> getAll(String codeBarre,String nom,String prenom,
                              String cin,
                              String organisme,
                              String groupe,
                              String acces,
                              Pageable pageable);



    @Query("SELECT DISTINCT p.groupe FROM Badge p ")
    List<String> findDistinctByGroupe();
}
