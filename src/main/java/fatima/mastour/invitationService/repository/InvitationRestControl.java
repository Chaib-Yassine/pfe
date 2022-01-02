package fatima.mastour.invitationService.repository;

import fatima.mastour.invitationService.entities.Invitation;
import fatima.mastour.invitationService.entities.Verifier;
import fatima.mastour.invitationService.service.DeclageLettre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class InvitationRestControl {

    @Autowired
    private InvitationRepository invitationRepository;
    private boolean exists;

    //check Invitation statut
    @GetMapping("/invitationAutorisation/{codeBare}")
    public Verifier getBadgeAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        try{
            Invitation invitation= invitationRepository.findByCodeBare(codeBare);

            if(invitation.getEtat()==true){
                verifier.setEtat(true);
                invitation.setEtat(false);
                invitationRepository.save(invitation);
            }else{
                verifier.setEtat(false);
            }}
        catch (Exception e){
            verifier.setEtat(false);
        }
        return verifier;
    }
    //genere des code barre

    @GetMapping("/genereInvitation")
    public boolean saveBadgeDefault(@Param("nbr") int nbr,@Param("titre") String titre){
        boolean etat=true;
        String code;
       try {
           DeclageLettre declageLettre =new DeclageLettre();
           for (int i =0;i<nbr ;i++){
               code=declageLettre.codeBarre();
               Invitation in = new Invitation();
               in.setEtat(true);
               in.setCodeBare(code);
               in.setType(titre.toUpperCase());
               invitationRepository.save(in);
               System.out.println(code);

           }
       }catch (Exception e){
           etat=false;
       }
        return etat;
    }


    @GetMapping("/invitationExist/{type}")
    public boolean getBadgeExist(@PathVariable(name = "type") String type){
       // System.out.println(cin.toUpperCase());
        try{
            exists = invitationRepository.existsByType(type.toUpperCase());
            System.out.println(exists);

        }
        catch (Exception e){
            System.out.println(e);
        }
        return exists;
    }

}
