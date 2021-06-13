package fatima.mastour.venteService.web;

import fatima.mastour.venteService.entities.Vente;
import fatima.mastour.venteService.entities.VenteDetail;
import fatima.mastour.venteService.feign.AddTickerServices;
import fatima.mastour.venteService.feign.TestRestClient;
import fatima.mastour.venteService.feign.VenteDetailRestClient;
import fatima.mastour.venteService.model.Article;
import fatima.mastour.venteService.model.Test;
import fatima.mastour.venteService.repository.VenteDetailRepository;
import fatima.mastour.venteService.repository.VenteRepository;
import fatima.mastour.venteService.service.DeclageLettre;
import org.springframework.web.bind.annotation.*;

@RestController
public class VenteRestController {
    private VenteRepository venteRepository;
    private VenteDetailRepository venteDetailRepository;
    private TestRestClient testRestClient;
    private VenteDetailRestClient venteDetailRestClient;
    private AddTickerServices addTickerServices;

    public VenteRestController(VenteRepository venteRepository, VenteDetailRepository venteDetailRepository, TestRestClient testRestClient, VenteDetailRestClient venteDetailRestClient, AddTickerServices addTickerServices) {
        this.venteRepository = venteRepository;
        this.venteDetailRepository = venteDetailRepository;
        this.testRestClient = testRestClient;
        this.venteDetailRestClient = venteDetailRestClient;
        this.addTickerServices = addTickerServices;
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

    //Enregistre Vente detaill
    @RequestMapping(value="/venteDetails",method = RequestMethod.POST)
    public VenteDetail saveVenteDetailsDefault(@RequestBody VenteDetail venteDetail){
        System.out.println(venteDetail.getQuantity());
        venteDetailRepository.save(venteDetail);
        for(int i=0 ;i<venteDetail.getQuantity();i++){
            String code;
            DeclageLettre declageLettre =new DeclageLettre();
            code= declageLettre.codeBarre("badge.getNom()","badge.getPrenom()");

            addTickerServices.addTicket(venteDetail.getArticleID(),code,true,venteDetail.getId());

            System.out.println(venteDetail.getArticleID()+" "+code+" "+true+" "+venteDetail.getId());

        }
        System.out.println(venteDetail.getPrice());

        return venteDetail;
    }
}
