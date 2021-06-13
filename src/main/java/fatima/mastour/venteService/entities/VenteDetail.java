package fatima.mastour.venteService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fatima.mastour.venteService.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class VenteDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;
    private Long articleID;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Vente vente;
    @Transient
    private Article article;
    @Transient
    private String articleName;

}
