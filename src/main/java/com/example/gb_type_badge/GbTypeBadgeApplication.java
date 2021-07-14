package com.example.gb_type_badge;

import com.example.gb_type_badge.Repository.TypeBadgeRepository;
import com.example.gb_type_badge.entites.TypeBadge;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GbTypeBadgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbTypeBadgeApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TypeBadgeRepository typeBadgeRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(TypeBadge.class);
        return args -> {

            typeBadgeRepository.save(new TypeBadge(null,"Organisation","organisation.png",true));
            typeBadgeRepository.save(new TypeBadge(null,"Service","service.png",true));
            typeBadgeRepository.save(new TypeBadge(null,"Prestataire","prestataire.png",true));
            typeBadgeRepository.save(new TypeBadge(null,"Service a","service a.png",true));
            typeBadgeRepository.save(new TypeBadge(null,"service a-b","service.png",true));
            typeBadgeRepository.findAll().forEach(System.out::println);
        };
    }

}
