package com.example.badge_impression.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data  @AllArgsConstructor  @NoArgsConstructor  @ToString
public class BadgeImpression {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idBadge;
    private String idUser;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;
}
