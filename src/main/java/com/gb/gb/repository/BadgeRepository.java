package com.gb.gb.repository;

import com.gb.gb.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BadgeRepository extends JpaRepository<Badge,Long> {
    Badge  findByCodeBare(String codeBare);
}
