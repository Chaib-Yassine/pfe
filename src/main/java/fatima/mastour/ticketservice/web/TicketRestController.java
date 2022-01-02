package fatima.mastour.ticketservice.web;


import fatima.mastour.ticketservice.Model.Article;
import fatima.mastour.ticketservice.entities.Ticket;
import fatima.mastour.ticketservice.entities.Verifier;
import fatima.mastour.ticketservice.feign.ArticleRestClient;
import fatima.mastour.ticketservice.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TicketRestController {
    @Autowired
    private ArticleRestClient articleRestClient ;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/fullTicket")
    public Page<Ticket> getAllTickets(int page,int size){
        Page<Ticket> ticket=ticketRepository.findAll(PageRequest.of(page,size));
        ticket.forEach(b->{
            b.setArticle(articleRestClient.findCustomerById(b.getIdArticle()));

        });
        return ticket;
    }
    @GetMapping(path = "/byNvente")
    public Page<Ticket> getAllTicketsNvente(int page,int size,int mc){
        Page<Ticket> ticket=ticketRepository.findAllByNventeEquals( mc,PageRequest.of(page,size));
        ticket.forEach(b->{
            b.setArticle(articleRestClient.findCustomerById(b.getIdArticle()));

        });
        return ticket;
    }

    //check Ticket statut
    @GetMapping("/ticketAutorisation/{codeBare}")
    public Verifier getTicketAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        try{
            Ticket ticket= ticketRepository.findByCodebarre(codeBare);
            System.out.println(ticket);
            if(ticket.getEtat()==true){
                verifier.setEtat(true);
                ticket.setEtat(false);
                ticketRepository.save(ticket);
            }else{
                verifier.setEtat(false);
            }
        }
        catch (Exception e){
            verifier.setEtat(false);
        }
        return verifier;
    }
    ///add ticket
    @RequestMapping(value="/saveTicket/)",method = RequestMethod.POST)
    public Ticket saveTicket(@RequestBody Ticket ticket){
        System.out.println("test");
       // System.out.println(ticket.getCodeBarre());


        return ticket;
    }
}
