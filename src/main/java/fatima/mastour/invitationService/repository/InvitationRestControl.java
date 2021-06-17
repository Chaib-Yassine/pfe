package fatima.mastour.invitationService.repository;

import fatima.mastour.invitationService.entities.Invitation;
import fatima.mastour.invitationService.entities.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvitationRestControl {

    @Autowired
    private InvitationRepository invitationRepository;


    //check Invitation statut
    @GetMapping("/invitationAutorisation/{codeBare}")
    public Verifier getBadgeAutorisation(@PathVariable(name = "codeBare") String codeBare){
        Verifier verifier=new Verifier();
        try{
            Invitation invitation= invitationRepository.findByCodeBare(codeBare);

            if(invitation.getEtat()==true){
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
