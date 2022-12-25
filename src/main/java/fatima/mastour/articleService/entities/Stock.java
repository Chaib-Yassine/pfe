package fatima.mastour.articleService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telephone;
    private String responsable;
    private String adresse;
    private String ville;
    private boolean Etat;
    public Date create;
    public Date update;

    @ManyToMany(mappedBy = "likeStock")
    Set<Article> likes;
}
