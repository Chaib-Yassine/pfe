package com.gb.gb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data 
public class TypeBadge {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String titre;
    private String chart;
    private boolean statut;
}
