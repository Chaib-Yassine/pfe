package com.example.badge_impression;

import com.example.badge_impression.Entites.BadgeImpression;
import com.example.badge_impression.Repository.BadgeImpressionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class BadgeImpressionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadgeImpressionApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BadgeImpressionRepository badgeImpressionRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(BadgeImpression.class);
        return args -> {
            badgeImpressionRepository.save(new BadgeImpression(null,1,1,new Date()));
            badgeImpressionRepository.save(new BadgeImpression(null,1,1,new Date()));
            badgeImpressionRepository.save(new BadgeImpression(null,1,1,new Date()));
            badgeImpressionRepository.save(new BadgeImpression(null,2,1,new Date()));
            badgeImpressionRepository.save(new BadgeImpression(null,2,1,new Date()));
            badgeImpressionRepository.findAll().forEach(System.out::println);
        };
    }
}
