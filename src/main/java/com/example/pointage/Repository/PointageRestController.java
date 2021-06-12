package com.example.pointage.Repository;


import com.example.pointage.Feign.VerifierService;

import com.example.pointage.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.pointage.entites.Pointage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PointageRestController {

    @Autowired
    private PointageRepository pointageRepository;
    @Autowired
    private  VerifierService verifierService;

    /*@Autowired
    private TypeBadgeServices typeBadgeServices;*/


   @RequestMapping(value="/pointages",method = RequestMethod.POST)
   public Verifier savePointageDefault(@RequestBody Pointage pointage){
       String lettreNum="";
       System.out.println(pointage.getCodeBarre());
       System.out.println(pointage.getTourniquet());
       switch (pointage.getCodeBarre().toLowerCase().substring(0,2)){
           case "BD":lettreNum="Badge";
               break;
           case "TK":lettreNum="Ticket";
               break;
           case "IN":lettreNum="Invitation";
               break;
           default:
               lettreNum = "-";
               break;
       }
       pointage.setTypeTitreAcces(lettreNum);
       pointage.setDate(new Date());

      if(verifierService.etatVerification(pointage.getCodeBarre()).getEtat()==true){
          pointage.setAutorization(true);
      }else{
          pointage.setAutorization(false);
      }
       pointageRepository.save(pointage);
       Verifier verifier = new Verifier();
       verifier.setEtat( verifierService.etatVerification(pointage.getCodeBarre()).getEtat());

       return verifier;
   }
    @GetMapping("/fullPointage/")
    public List<Pointage> getAllPointage(){
        List<Pointage> pointage=pointageRepository.findAll();
        return pointage;
    }


}
