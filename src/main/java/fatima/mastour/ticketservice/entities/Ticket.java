package fatima.mastour.ticketservice.entities;

import fatima.mastour.ticketservice.Model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idArticle;
    private String codeBarre;
    private Boolean etat;
    private int  nVente;
    @Transient
    private Article article;
}



