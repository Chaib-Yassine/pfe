package com.example.badge_impression.Web;

import com.example.badge_impression.Entites.BadgeImpression;
import com.example.badge_impression.Repository.BadgeImpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestBadgeImpressionController {
	@Autowired
	private BadgeImpressionRepository badgeRepository;

	@GetMapping("/listBadgesImpression")
	public List<BadgeImpression> list(){
		return badgeRepository.findAll();
	}
	
	@GetMapping("/listBadgesImpression/{id}")
	public BadgeImpression getPne(@PathVariable Long id){
		return badgeRepository.findById(id).get();
	}

	@GetMapping("/listBadges/{idBadge}")
	public List<BadgeImpression> listBadge(@PathVariable int idBadge){
		return badgeRepository.findByIdBadge(idBadge);
	}
}
