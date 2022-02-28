package org.inti.Repository;

/*
import com.example.pointage.Feign.VerifierBadgeService;
import com.example.pointage.Feign.VerifierInvitationService;
import com.example.pointage.Feign.VerifierTicketService;
import com.example.pointage.entites.Pointage;
import com.example.pointage.model.Verifier;*/
import org.inti.Feign.VerifierBadgeService;
import org.inti.Feign.VerifierInvitationService;
import org.inti.Feign.VerifierTicketService;
import org.inti.entites.Pointage;
import org.inti.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
public class PointageRestController {

    @Autowired
    private PointageRepository pointageRepository;
    @Autowired
    private VerifierBadgeService verifierBadgeService;
    @Autowired
    private VerifierInvitationService verifierInvitationService;
    @Autowired
    private VerifierTicketService verifierTicketService;

    //////////
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //////////
    DateTimeFormatter toFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 1, false)
            .toFormatter();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime ldt2 = LocalDateTime.parse(sdf.format(dt), formatter);
    ///////////

    private static  int cpt = 0;
    @GetMapping("/fullPointage/")
    public Flux<Pointage> getPointage(){
        return pointageRepository.findAll();
    }
    @GetMapping("/pointage/{id}")
    public Mono<Pointage> getPointage(@PathVariable Integer id){
        return pointageRepository.findById(id);
    }

    @PostMapping("/pointage")
    public Mono<Pointage> createPointage(@RequestBody Pointage pointage){
        System.out.println(pointage.toString());
        return pointageRepository.save(pointage);
    }

    @PutMapping("/pointage/{id}")
    public Mono<Pointage> updatePointage(@RequestBody Pointage pointage,@PathVariable Integer id){
        pointage.setId(id);
        return pointageRepository.save(pointage);
    }
    @DeleteMapping("/pointage/{id}")
    public Mono<Void> deletePointage(@PathVariable Integer id){
        return pointageRepository.deleteById(id);
    }

    @GetMapping(value = "/pointages/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pointage> streamA(@PathVariable Integer id){
        return pointageRepository.findById(id)
                .flatMapMany(soc->{
                    Flux<Long> interval=Flux.interval(Duration.ofSeconds(1));
                    Flux<Pointage> transactionFlux= Flux.fromStream(Stream.generate(()->{
                        Pointage transaction=new Pointage();
                        transaction.setDate(ldt2);
                        transaction.setPorte("P1");
                        transaction.setTourniquet("T12");
                        transaction.setTypetitreacces("Badge");
                        transaction.setAutorization(true);
                        return transaction;
                    }));
                    return Flux.zip(interval,transactionFlux)
                            .map(data->{
                                return data.getT2();
                            }).share();
                });
    }
    @GetMapping(value = "/streamPointage",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pointage> streamTransactions() throws Exception {
        Flux<Long> interval=Flux.interval(Duration.ofSeconds(1));
        Flux<Pointage> transactionFlux= Flux.fromStream(Stream.generate(()->{
            Pointage pointage = new Pointage();
            // transactionRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(cpt, 1)).subscribe(System.out::println);
            Flux<Pointage> tr= pointageRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(cpt, 1));
            //Flux<List<Transaction>> tran = transactionRepository.findAll().collectList().flatMapMany(Flux::just);
            tr.subscribe(x-> pointage.setDate(x.getDate()));
            tr.subscribe(x-> pointage.setPorte(x.getPorte()));
            tr.subscribe(x-> pointage.setId(x.getId()));
            tr.subscribe(x-> pointage.setTourniquet(x.getTourniquet()));
            tr.subscribe(x-> pointage.setTypetitreacces(x.getTypetitreacces()));
            tr.subscribe(x-> pointage.setAutorization(x.getAutorization()));
            tr.subscribe(x->cpt++);
            return pointage ;
        }));

        return   Flux.zip(interval,transactionFlux)
                .map(data->{
                    return data.getT2();
                }).share();
    }



   @RequestMapping(value="/check",method = RequestMethod.GET)
   @CrossOrigin("*")
   public Verifier savePointageDefault(@RequestParam(name = "code",defaultValue = "")String code,
                                       @RequestParam(name = "trq",defaultValue = "")String trq,
                                       @RequestParam(name = "port",defaultValue = "")String port){
       Pointage pointage=new Pointage();
       pointage.setCodebarre(code);
       pointage.setTourniquet(trq);
       pointage.setPorte(port);

       String lettreNum="";

        Boolean status =null    ;
       switch (pointage.getCodebarre().toUpperCase().substring(0,2)){
           case "BD":lettreNum="Badge";
               if(verifierBadgeService.badgeVerification(pointage.getCodebarre()).getEtat()==true){
                   pointage.setAutorization(true);
                   status=true;
               }else{
                   pointage.setAutorization(false);
                   status=false;
               }
               break;
           case "TK":lettreNum="Ticket";
               //System.out.println(verifierTicketService.ticketVerification(pointage.getCodebarre()).getEtat());
               if(verifierTicketService.ticketVerification(pointage.getCodebarre()).getEtat()==true){
                   pointage.setAutorization(true);
                   status=true;
               }else{
                   pointage.setAutorization(false);
                   status=false;
               }
               break;
           case "IN":lettreNum="Invitation";
               if(verifierInvitationService.invitationVerification(pointage.getCodebarre()).getEtat()==true){
                   pointage.setAutorization(true);
                   status=true;
               }else{
                   pointage.setAutorization(false);
                   status=false;
               }
               break;
           default:
               lettreNum = "-";
               break;
       }
       pointage.setTypetitreacces(lettreNum);
       pointage.setDate(ldt2);
       createPointage(pointage).subscribe();
       Verifier verifier = new Verifier();
       verifier.setEtat( status);
       return verifier;
   }
 /*   @GetMapping("/fullPointage/")
    public List<Pointage> getAllPointage(){
        List<Pointage> pointage=pointageRepository.findAll();
        return pointage;
    }

    @RequestMapping(value="/byAttre",method = RequestMethod.GET)
    @CrossOrigin("*")
    public Page<Pointage> getAllPointage(@RequestParam(name = "codeBarre",defaultValue = "")String codeBarre,
                                       @RequestParam(name = "porte",defaultValue = "")String porte,
                                       @RequestParam(name = "tourniquet",defaultValue = "")String tourniquet,
                                       @RequestParam(name = "typetitreacces",defaultValue = "")String typetitreacces,
                                      // @Param("autorization")String autorization,
                                      // @Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                      // @Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
                                        @Param("page")int page,
                                         @Param("size")int size){
        System.out.println(codeBarre);
        System.out.println(porte);
        System.out.println(page);
        System.out.println(tourniquet);
        System.out.println(typetitreacces);

        Page<Pointage> pointage=pointageRepository.getAll(codeBarre,PageRequest.of(page,size));
        pointage.forEach(p->{
            System.out.println("--------------");
            System.out.println(p.getId());
            System.out.println( p.getCodebarre());
        });
   return pointage;
   }
*/

}
