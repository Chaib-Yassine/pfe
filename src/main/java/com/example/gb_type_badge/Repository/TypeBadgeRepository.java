package com.example.gb_type_badge.Repository;

import com.example.gb_type_badge.entites.TypeBadge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface TypeBadgeRepository extends JpaRepository<TypeBadge,Long> {


}
