package com.example.pointage.entites;

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
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Pointage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codebarre;
    private String porte;
    private String tourniquet;
    private Date date;
    private String typetitreacces;
    private Boolean autorization;
}
