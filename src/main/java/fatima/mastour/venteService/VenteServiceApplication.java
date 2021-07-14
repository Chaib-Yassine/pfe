package fatima.mastour.venteService;

import fatima.mastour.venteService.entities.Vente;
import fatima.mastour.venteService.entities.VenteDetail;
import fatima.mastour.venteService.feign.VenteDetailRestClient;
import fatima.mastour.venteService.model.Article;
import fatima.mastour.venteService.repository.VenteDetailRepository;
import fatima.mastour.venteService.repository.VenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class VenteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            VenteRepository venteRepository,
            VenteDetailRepository venteDetailRepository,
            VenteDetailRestClient venteDetailRestClient,
             RepositoryRestConfiguration restConfiguration
            ){restConfiguration.exposeIdsFor(Vente.class);
        return args -> {
            Vente vent1 = venteRepository.save(new Vente(null, 123.0, new Date(),  "User2",null));
            PagedModel< Article > articlePagedModel=venteDetailRestClient.pageArticles();
            articlePagedModel.forEach(p->{
                VenteDetail venteDetail = new VenteDetail();
                venteDetail.setPrice(p.getPrice());
                venteDetail.setQuantity(1+new Random().nextInt(100));
                venteDetail.setVente(vent1);
                venteDetail.setArticleID((long) p.getId());
                venteDetail.setVenteIds(null);
                venteDetailRepository.save(venteDetail);
           });
        };
    }

}

