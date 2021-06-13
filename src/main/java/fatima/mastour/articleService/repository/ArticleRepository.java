package fatima.mastour.articleService.repository;

import fatima.mastour.articleService.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository < Article, Long >{
}
