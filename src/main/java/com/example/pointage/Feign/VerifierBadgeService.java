package com.example.pointage.Feign;


import com.example.pointage.model.Verifier;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "BADGES-SERVICE")
public interface VerifierBadgeService {

    @GetMapping("/badgeAutorisation/{codeBare}")
    public Verifier badgeVerification(@PathVariable String codeBare);

}
