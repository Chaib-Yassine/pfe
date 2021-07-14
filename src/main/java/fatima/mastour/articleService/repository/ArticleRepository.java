package fatima.mastour.articleService.repository;

import fatima.mastour.articleService.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ArticleRepository extends JpaRepository < Article, Long >{
    @RestResource(path = "/byDesignation")
    public Page<Article> findArticleByDesignationContains(@Param("mc")String titre, Pageable pageable);
}
