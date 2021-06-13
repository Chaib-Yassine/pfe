package fatima.mastour.venteService;

import fatima.mastour.venteService.entities.Vente;
import fatima.mastour.venteService.entities.VenteDetail;
import fatima.mastour.venteService.feign.TestRestClient;
import fatima.mastour.venteService.feign.VenteDetailRestClient;
import fatima.mastour.venteService.model.Article;
import fatima.mastour.venteService.model.Test;
import fatima.mastour.venteService.repository.VenteDetailRepository;
import fatima.mastour.venteService.repository.VenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
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
            TestRestClient testRestClient,
            VenteDetailRestClient venteDetailRestClient
            ){
        return args -> {
            Test test = testRestClient.getTestById(1L);
            Vente vent1 = venteRepository.save(new Vente(null, 123.0, new Date(),  1,null, test.getId(), null));
            PagedModel< Article > articlePagedModel=venteDetailRestClient.pageArticles();
            articlePagedModel.forEach(p->{
                VenteDetail venteDetail = new VenteDetail();
                venteDetail.setPrice(p.getPrice());
                venteDetail.setQuantity(1+new Random().nextInt(100));
                venteDetail.setVente(vent1);
                venteDetail.setArticleID((long) p.getId());
                venteDetailRepository.save(venteDetail);
           });
        };
    }

}

