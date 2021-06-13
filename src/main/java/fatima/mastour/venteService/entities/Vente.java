package fatima.mastour.venteService.entities;

import fatima.mastour.venteService.model.Test;
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
    private Date VenteDate;
    //private double quantity;
    private int user_id;
    @OneToMany(mappedBy="vente")
    private Collection<VenteDetail> venteDetails;
    private Long testID;
    @Transient
    private Test test;

}
