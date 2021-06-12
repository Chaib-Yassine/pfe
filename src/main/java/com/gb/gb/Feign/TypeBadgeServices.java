package com.gb.gb.Feign;


import com.gb.gb.model.TypeBadge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="TYPE-BADGE-SERVICE")
public interface TypeBadgeServices {
	@GetMapping("/typeBadges/{id}")
	public TypeBadge findCustomerById(@PathVariable (name = "id") Long id);

}
