package com.gb.gb.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gb.gb.model.Badge_impression;
import com.gb.gb.model.TypeBadge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Badge {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
  private String organisme;
    private String groupe;
    private String codebare;
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private Long typebadgeid;
    @Transient
    private TypeBadge typeBadge;
    private Date date;
    private String id_user;
    private Boolean etat;
     @Transient
     private List<Badge_impression> Badge_impression;

}
