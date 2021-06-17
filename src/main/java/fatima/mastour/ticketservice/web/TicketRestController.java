package fatima.mastour.ticketservice.web;


import fatima.mastour.ticketservice.Model.Article;
import fatima.mastour.ticketservice.entities.Ticket;
import fatima.mastour.ticketservice.entities.Verifier;
import fatima.mastour.ticketservice.feign.ArticleRestClient;
import fatima.mastour.ticketservice.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketRestController {
    @Autowired
    private ArticleRestClient articleRestClient ;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/fullTicket/")
    public String  getAllTickets(){
       // List<Ticket> ticket = ticketRepository.findAll();
        System.out.println("ici");
/*
        ticket.forEach(t->{
            Article article = articleRestClient.getArticleById((long) t.getIdArticle());
            t.setArticle(article);
        });*/
        return "test";
    }

    //check Ticket statut
    @GetMapping("/ticketAutorisation/{codeBare}")
    public Verifier getTicketAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        try{
            Ticket ticket= ticketRepository.findByCodeBarre(codeBare);

            if(ticket.getEtat()==true){
                verifier.setEtat(true);
            }else{
                verifier.setEtat(false);
            }}
        catch (Exception e){
            verifier.setEtat(false);
        }
        return verifier;
    }
}
