package com.example.gb_type_badge.Repository;

import com.example.gb_type_badge.entites.TypeBadge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RepositoryRestResource
@CrossOrigin("*")
public interface TypeBadgeRepository extends JpaRepository<TypeBadge,Long> {

    @RestResource(path = "/byTitre")
    public Page<TypeBadge> findTypeBadgeByTitreContains(@Param("mc")String titre, Pageable pageable);

    @RestResource(path = "/byTitre1")
    public Page<TypeBadge> findTypeBadgeByTitreLike(@Param("mc")String titre, Pageable pageable);

}
