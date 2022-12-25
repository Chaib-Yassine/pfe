package fatima.mastour.articleService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class Prix {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idArticle;
    private double prixParticulier;
    private double prixGrossiste;
    private double prixRevendeur1;
    private double prixrevendeur2;
    public Date create;
    public Date update;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "prix")
    private Article article;


}
