package fatima.mastour.articleService;

import fatima.mastour.articleService.entities.Article;
import fatima.mastour.articleService.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ArticleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ArticleRepository articleRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Article.class);
        return args -> {

            articleRepository.save(new Article(null, "ticket1", 100,10));
            articleRepository.save(new Article(null, "ticket2", 200,20));
            articleRepository.save(new Article(null, "ticket3", 300,30));
        };
    }

}
