package org.inti.Feign;


import org.inti.model.Verifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "TICKET-SERVICE")
public interface VerifierTicketService {
    @GetMapping("/ticketAutorisation/{codeBare}")
    public Verifier ticketVerification(@PathVariable String codeBare);

}
