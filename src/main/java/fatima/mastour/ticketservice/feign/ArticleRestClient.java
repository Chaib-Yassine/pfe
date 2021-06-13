package fatima.mastour.ticketservice.feign;


import fatima.mastour.ticketservice.Model.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ARTICLE-SERVICE")
public interface ArticleRestClient {
    @GetMapping(path = "/articles")
    PagedModel <Article> pageArticles();

    @GetMapping(path="/articles/{id}")
    Article getArticleById(@PathVariable Long id);

}

