package fatima.mastour.articleService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString

public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date dateAjoute;
    private String designation;
    private String codeBArre;
    private String codeBArrePacket;
    private String reference;
    private int quantite;
    private String lienPhoto;
    public Date create;
    public Date update;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prix" )
    private Prix prix;

    @ManyToMany
    @JoinTable(
            name = "FamilleProduitLike",
            joinColumns = @JoinColumn(name = "Article_id"),
            inverseJoinColumns = @JoinColumn(name = "FamilleProduit_id"))
    Set<FamilleProduit> likeFamilleProduit;

    @ManyToMany
    @JoinTable(
            name = "StockLike",
            joinColumns = @JoinColumn(name = "Article_id"),
            inverseJoinColumns = @JoinColumn(name = "Stock_id"))
    Set<FamilleProduit> likeStock;

}
