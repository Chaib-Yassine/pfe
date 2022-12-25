package fatima.mastour.articleService.repository;

import fatima.mastour.articleService.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock , Long> {
}
