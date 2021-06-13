package fatima.mastour.venteService.model;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String designation;
    private double price;
    private double quantity;


}
