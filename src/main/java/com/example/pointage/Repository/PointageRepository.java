package com.example.pointage.Repository;

import com.example.pointage.entites.Pointage;
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
public interface PointageRepository extends JpaRepository<Pointage,Long> {
    @RestResource(path = "/byAttre")
   // @Query(value = "SELECT * FROM Pointage WHERE codebarre like :codeBarre  and porte like :porte and tourniquet like :tourniquet and typeTitreAcces like :typeTitreAcces and autorization like :autorization and  date >= :startDate AND date <= :endDate", nativeQuery = true)
    @Query(value = "SELECT * FROM Pointage WHERE codebarre like %:codeBarre%  and porte like %:porte%  and tourniquet like %:tourniquet% and typetitreacces like %:typetitreacces%", nativeQuery = true)
    public Page<Pointage> getAll(
            @Param("codeBarre")String codeBarre,
            @Param("porte")String porte,
            @Param("tourniquet")String tourniquet,
            @Param("typetitreacces")String typetitreacces,
            //@Param("autorization")String autorization,
            //@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
           // @Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Pageable pageable);
}
