package fatima.mastour.testservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class Test {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY )
    public Long id;
    public String name;
    public String email;
}
