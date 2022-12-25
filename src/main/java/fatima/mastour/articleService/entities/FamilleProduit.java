package fatima.mastour.articleService.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data @ToString  @AllArgsConstructor @NoArgsConstructor
public class FamilleProduit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String libelle;
    private String Parent;
    private boolean etat;
    public Date create;
    public Date update;
    @ManyToMany(mappedBy = "likeFamilleProduit")
    Set<Article> likes;

}
