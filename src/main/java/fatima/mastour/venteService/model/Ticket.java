package fatima.mastour.venteService.model;

import lombok.Data;


@Data

public class Ticket {

    private Long id;
    private Long idArticle;
    private String codeBarre;
    private Boolean etat;
    private int  nVente;
}



