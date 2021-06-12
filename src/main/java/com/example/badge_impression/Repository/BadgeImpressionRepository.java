package com.example.badge_impression.Repository;

import com.example.badge_impression.Entites.BadgeImpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface BadgeImpressionRepository extends JpaRepository<BadgeImpression,Long> {


    List<BadgeImpression> findByIdBadge(int idBadge);
}
