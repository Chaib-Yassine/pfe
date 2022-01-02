package fatima.mastour.venteService.model;

import lombok.Data;


@Data

public class Ticket {

    private Long id;
    private Long idArticle;
    private String codebarre;
    private Boolean etat;
    private int  nVente;
}



