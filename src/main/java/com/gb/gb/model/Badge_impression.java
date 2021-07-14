package com.gb.gb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gb.gb.entities.Badge;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class Badge_impression {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
   // @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private int idBadge;
    private String idUser;
    private Date date;
}
