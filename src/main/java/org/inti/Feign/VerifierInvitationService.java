package org.inti.Feign;


import org.inti.model.Verifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "INVITATION-SERVICE")
public interface VerifierInvitationService {
    @GetMapping("/invitationAutorisation/{codeBare}")
    public Verifier invitationVerification(@PathVariable String codeBare);

}
