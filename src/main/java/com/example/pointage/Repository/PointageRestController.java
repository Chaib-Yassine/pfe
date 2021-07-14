package com.example.pointage.Repository;



import com.example.pointage.Feign.VerifierBadgeService;
import com.example.pointage.Feign.VerifierInvitationService;
import com.example.pointage.Feign.VerifierTicketService;
import com.example.pointage.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.pointage.entites.Pointage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    /*@Autowired
    private TypeBadgeServices typeBadgeServices;*/


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
       System.out.println(lettreNum);
       pointage.setDate(new Date());
       pointageRepository.save(pointage);
       Verifier verifier = new Verifier();
       verifier.setEtat( status);

       return verifier;
   }
    @GetMapping("/fullPointage/")
    public List<Pointage> getAllPointage(){
        List<Pointage> pointage=pointageRepository.findAll();
        return pointage;
    }


}
