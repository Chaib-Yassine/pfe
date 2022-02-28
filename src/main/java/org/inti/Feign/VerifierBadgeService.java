package org.inti.Feign;



import org.inti.model.Verifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "BADGES-SERVICE")
public interface VerifierBadgeService {

    @GetMapping("/badgeAutorisation/{codeBare}")
    public Verifier badgeVerification(@PathVariable String codeBare);

}