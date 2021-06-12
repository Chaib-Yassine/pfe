package com.example.pointage.Repository;

import com.example.pointage.entites.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PointageRepository extends JpaRepository<Pointage,Long> {


}
