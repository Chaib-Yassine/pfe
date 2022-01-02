package com.gb.gb.repository;

import com.gb.gb.Feign.ImpressionService;
import com.gb.gb.Feign.TypeBadgeServices;

import com.gb.gb.entities.Badge;
import com.gb.gb.entities.Verifier;
import com.gb.gb.model.TypeBadge;
import com.gb.gb.service.DeclageLettre;
import com.sun.tools.jconsole.JConsolePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BadgeRestController {
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private ImpressionService impressionService;
    @Autowired
    private TypeBadgeServices typeBadgeServices;
    private boolean exists;

    @GetMapping("/groupe")
    public List<String> getGroupe(){
        return badgeRepository.findDistinctByGroupe();
    }

//getbadge
    @GetMapping("/fullBadge/{id}")
    public Badge getBadge(@PathVariable(name = "id") Long id){
        Badge badge=badgeRepository.findById(id).get();
        badge.setTypeBadge(typeBadgeServices.findCustomerById(badge.getTypebadgeid()));
        badge.setBadge_impression( impressionService.listBadge(badge.getId()));

        return badge;
    }
    @GetMapping("/fullBadge")
    public Page<Badge> getAllBadge(int page,int size){
        Page<Badge> badge=badgeRepository.findAll(PageRequest.of(page,size));
        badge.forEach(b->{
            b.setTypeBadge(typeBadgeServices.findCustomerById(b.getTypebadgeid()));
            b.setBadge_impression( impressionService.listBadge(b.getId()));
        });
        return badge;
    }
    @GetMapping("/byAttres")
    public Page<Badge> getAllBadger(@Param("codeBarre") String codeBarre,
                                    @Param("nom") String nom,
                                    @Param("prenom") String prenom,
                                    @Param("cin") String cin,
                                    @Param("groupe") String groupe,
                                    @Param("organisme") String organisme,
                                    @Param("acces") String acces,
                                    @Param("page")int page,
                                    @Param("size")int size){
        System.out.println(acces);
       // Page<Badge> badge=badgeRepository.findAll(PageRequest.of(page,size));
        Page<Badge> badge=badgeRepository.getAll(codeBarre,nom,prenom,cin,organisme,groupe,acces,PageRequest.of(page,size));
        badge.forEach(b->{
            b.setTypeBadge(typeBadgeServices.findCustomerById(b.getTypebadgeid()));
            b.setBadge_impression( impressionService.listBadge(b.getId()));
        });
        return badge;
    }
    //save
    @RequestMapping(value="/saveBadgesCb",method = RequestMethod.POST)
    public Badge saveBadgeDefault( @RequestBody Badge badge){
        String code;
        DeclageLettre declageLettre =new DeclageLettre();
        badge.setDate(new Date());
        TypeBadge typeBadge=typeBadgeServices.findCustomerById(badge.getTypebadgeid());
        //badge.setTypebadgeid(typeBadge.getId());
        // System.out.println(badge.getNom()+"  (------)  "+badge.getPrenom()+"  (------) "+badge.getCodeBare());
        Badge badge1= badgeRepository.save(badge);
        System.out.println(badge1.getId());
        code= declageLettre.codeBarre(badge.getNom(),badge.getPrenom(),badge1.getId());
        badge.setCodebare(code);
        if (badge.getEtat()==null){
            badge.setEtat(false);
        }else{
            badge.setEtat(true);
        }
        badge.setCin(badge.getCin().toUpperCase());
        badgeRepository.save(badge);
       // impressionService.addImpression(badge.getId(),badge.getId_user());
        return badge;
    }

    // update
    @RequestMapping(value="/updateBadgesCb",method = RequestMethod.POST)
    public Badge updateBadgeDefault( @RequestBody Badge badge){
        //System.out.println(badge);
        badge.setDate(new Date());
        Badge badgeUp=getBadge(badge.getId());
        badgeUp.setNom(badge.getNom());
        badgeUp.setPrenom(badge.getPrenom());
        badgeUp.setOrganisme(badge.getOrganisme());
        badgeUp.setGroupe(badge.getGroupe());
        badgeUp.setCin(badge.getCin().toUpperCase());
        if (badge.getEtat()==null){
            badgeUp.setEtat(false);
        }else{
            badgeUp.setEtat(true);
        }
        TypeBadge typeBadge=typeBadgeServices.findCustomerById(badge.getTypebadgeid());
        badgeUp.setTypebadgeid(typeBadge.getId());
       // System.out.println("$$$$$$$$$$$");
       // System.out.println(badgeUp);

        badgeRepository.save(badgeUp);
        return badge;
    }


    //check badge statut
    @GetMapping("/badgeAutorisation/{codeBare}")
    public Verifier getBadgeAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        System.out.println(codeBare.toUpperCase());
        try{
           Badge badge= badgeRepository.findByCodebare(codeBare.toUpperCase());
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

    //check badge Exist
    @GetMapping("/badgeExist/{cin}")
    public boolean getBadgeExist(@PathVariable(name = "cin") String cin){
        System.out.println(cin.toUpperCase());
        try{
            exists = badgeRepository.existsByCin(cin.toUpperCase());
            System.out.println(exists);

        }
        catch (Exception e){
            System.out.println("intouvable cin");
        }
        return exists;
    }


    @GetMapping("/badgeCode/{id}")
    public boolean getChangeCode(@PathVariable(name = "id") Long id){
        Badge badgeUp=getBadge(id);
        System.out.println(badgeUp.getCodebare());
        DeclageLettre declageLettre =new DeclageLettre();
        badgeUp.setCodebare(declageLettre.codeBarre(badgeUp.getNom(),badgeUp.getPrenom(),badgeUp.getId()));
        badgeRepository.save(badgeUp);
        System.out.println(badgeUp.getCodebare());
        return true;
    }



}
