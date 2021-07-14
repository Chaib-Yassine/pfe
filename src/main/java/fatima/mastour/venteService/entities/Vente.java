package fatima.mastour.venteService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Data @NoArgsConstructor
@AllArgsConstructor

public class Vente {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double prix_total;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date ventedate;
    //private double quantity;
    private String user_id;
    @OneToMany(mappedBy="vente")
    private Collection<VenteDetail> venteDetails;
    @PrePersist
    private void onCreate() {
        ventedate = new Date();
    }
}
