package com.gb.gb.Feign;


import com.gb.gb.model.Badge_impression;
import com.gb.gb.model.TypeBadge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@FeignClient(name = "IMPRESSION-BADGE-SERVICE")
public interface ImpressionService {

    @GetMapping("/listBadges/{idBadge}")
    public List<Badge_impression> listBadge(@PathVariable Long idBadge);

    @PostMapping("/badgeImpressions/")
    public Badge_impression addImpression(@PathVariable Long idBadge,@PathVariable int idUser);
}
