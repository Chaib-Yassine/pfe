package fatima.mastour.venteService.web;

import fatima.mastour.venteService.entities.Vente;
import fatima.mastour.venteService.feign.TestRestClient;
import fatima.mastour.venteService.feign.VenteDetailRestClient;
import fatima.mastour.venteService.model.Article;
import fatima.mastour.venteService.model.Test;
import fatima.mastour.venteService.repository.VenteDetailRepository;
import fatima.mastour.venteService.repository.VenteRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VenteRestController {
    private VenteRepository venteRepository;
    private VenteDetailRepository venteDetailRepository;
    private TestRestClient testRestClient;
    private VenteDetailRestClient venteDetailRestClient;

    public VenteRestController(VenteRepository venteRepository, VenteDetailRepository venteDetailRepository, TestRestClient testRestClient, VenteDetailRestClient venteDetailRestClient) {
        this.venteRepository = venteRepository;
        this.venteDetailRepository = venteDetailRepository;
        this.testRestClient = testRestClient;
        this.venteDetailRestClient = venteDetailRestClient;
    }

    @GetMapping(path = "/fullVente/{id}")
    public Vente getVente(@PathVariable(name="id") Long id){
        Vente vente = venteRepository.findById(id).get();
        Test test = testRestClient.getTestById(vente.getTestID());
        vente.setTest(test);
        vente.getVenteDetails().forEach(pi->{
           Article article = venteDetailRestClient.getArticleById(pi.getArticleID());
            pi.setArticle(article);
            //pi.setArticleName(article.getName());
        });
        return vente;
    }
}
