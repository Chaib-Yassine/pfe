package com.example.badge_impression.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data  @AllArgsConstructor  @NoArgsConstructor  @ToString
public class BadgeImpression {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idBadge;
    private int idUser;
    private Date date;
}
