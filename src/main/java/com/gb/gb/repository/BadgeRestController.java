package com.gb.gb.repository;

import com.gb.gb.Feign.ImpressionService;
import com.gb.gb.Feign.TypeBadgeServices;

import com.gb.gb.entities.Badge;
import com.gb.gb.entities.Verifier;
import com.gb.gb.model.TypeBadge;
import com.gb.gb.service.DeclageLettre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class BadgeRestController {
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private ImpressionService impressionService;
    @Autowired
    private TypeBadgeServices typeBadgeServices;


//getbadge
    @GetMapping("/fullBadge/{id}")
    public Badge getBadge(@PathVariable(name = "id") Long id){
        Badge badge=badgeRepository.findById(id).get();
        badge.setTypeBadge(typeBadgeServices.findCustomerById(badge.getTypeBadgeID()));
        badge.setBadge_impression( impressionService.listBadge(badge.getId()));

        return badge;
    }
    @GetMapping("/fullBadge/")
    public List<Badge> getAllBadge(){
        List<Badge> badge=badgeRepository.findAll();
        badge.forEach(b->{
            b.setTypeBadge(typeBadgeServices.findCustomerById(b.getTypeBadgeID()));
            b.setBadge_impression( impressionService.listBadge(b.getId()));
        });

        return badge;
    }

    //enregistre Badge avec impression
    @RequestMapping(value="/saveBadge",method = RequestMethod.POST)
    public Badge saveBadge( @RequestBody Badge badge){
        badge.setCodeBare("testt");
        badge.setDate(new Date());
        TypeBadge typeBadge=typeBadgeServices.findCustomerById(1L);
        badge.setTypeBadgeID(typeBadge.getId());
        System.out.println(badge.getNom()+"  (------)  "+badge.getPrenom()+"  (------) "+badge.getCodeBare());
        badgeRepository.save(badge);
        impressionService.addImpression(badge.getId(),badge.getId_user());
        return badge;
    }
    //Enregistre Badge
    @RequestMapping(value="/saveBadgesCb",method = RequestMethod.POST)
    public Badge saveBadgeDefault( @RequestBody Badge badge){
        String code;
        DeclageLettre declageLettre =new DeclageLettre();
        code= declageLettre.codeBarre(badge.getNom(),badge.getPrenom());
        badge.setCodeBare(code);
        badge.setDate(new Date());
        TypeBadge typeBadge=typeBadgeServices.findCustomerById(1L);
        badge.setTypeBadgeID(typeBadge.getId());
       // System.out.println(badge.getNom()+"  (------)  "+badge.getPrenom()+"  (------) "+badge.getCodeBare());
        badgeRepository.save(badge);
       // impressionService.addImpression(badge.getId(),badge.getId_user());
        return badge;
    }

    @GetMapping("/badgeAutorisation/{codeBare}")
    public Verifier getBadgeAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        try{
           Badge badge= badgeRepository.findByCodeBare(codeBare);
           if(badge.getEtat()==true){
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
