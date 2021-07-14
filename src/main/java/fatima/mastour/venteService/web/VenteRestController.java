package fatima.mastour.venteService.web;

import fatima.mastour.venteService.entities.Vente;
import fatima.mastour.venteService.entities.VenteDetail;
import fatima.mastour.venteService.feign.AddTickerServices;
import fatima.mastour.venteService.feign.VenteDetailRestClient;
import fatima.mastour.venteService.model.Article;
import fatima.mastour.venteService.repository.VenteDetailRepository;
import fatima.mastour.venteService.repository.VenteRepository;
import fatima.mastour.venteService.service.DeclageLettre;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class VenteRestController {
    private VenteRepository venteRepository;
    private VenteDetailRepository venteDetailRepository;
    private VenteDetailRestClient venteDetailRestClient;
    private AddTickerServices addTickerServices;

    public VenteRestController(VenteRepository venteRepository, VenteDetailRepository venteDetailRepository,  VenteDetailRestClient venteDetailRestClient, AddTickerServices addTickerServices) {
        this.venteRepository = venteRepository;
        this.venteDetailRepository = venteDetailRepository;
        this.venteDetailRestClient = venteDetailRestClient;
        this.addTickerServices = addTickerServices;
    }

    @GetMapping(path = "/fullVente/{id}")
    public Vente getVente(@PathVariable(name="id") Long id){
        System.out.println(id);
        Vente vente = venteRepository.findById(id).get();
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
       // System.out.println(venteDetail.getVenteIds());

        Vente vente = venteRepository.findById(venteDetail.getVenteIds()).get();
        venteDetail.setVente(vente);
        //System.out.println(venteDetail.getQuantity());
        venteDetailRepository.save(venteDetail);

        for(int i=0 ;i<venteDetail.getQuantity();i++){
            String code;
            DeclageLettre declageLettre =new DeclageLettre();
            code= declageLettre.codeBarre("getNom()","badge.getPrenom()");

            addTickerServices.addTicket(venteDetail.getArticleID(),code,true,vente.getId());

           // System.out.println(venteDetail.getArticleID()+" "+code+" "+true+" "+vente.getId());

        }
       // System.out.println(venteDetail.getPrice());

        return venteDetail;
    }
}
