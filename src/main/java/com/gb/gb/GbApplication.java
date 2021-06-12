package com.gb.gb;

import com.gb.gb.Feign.ImpressionService;
import com.gb.gb.Feign.TypeBadgeServices;
import com.gb.gb.entities.Badge;
import com.gb.gb.model.TypeBadge;
import com.gb.gb.repository.BadgeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class GbApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbApplication.class, args);
    }

@Bean
    CommandLineRunner start(BadgeRepository badgeRepository, ImpressionService impressionService, TypeBadgeServices typeBadgeServices, RepositoryRestConfiguration restConfiguration){
    restConfiguration.exposeIdsFor(Badge.class);
        return args -> {
            TypeBadge typeBadge=typeBadgeServices.findCustomerById(1L);
            System.out.println(typeBadge.getTitre() );
            System.out.println(typeBadge.getChart() );
            System.out.println(typeBadge.getId() );
            badgeRepository.save(new Badge(null,"yassine","chaib","AD12","organisme","bp1","B009218",typeBadge.getId(),null, new Date(),12,false,null));
            badgeRepository.save(new Badge(null,"test","nom","AD31","organisme","bp1","B009210",typeBadge.getId(),null, new Date(),12,true,null));
            badgeRepository.findAll().forEach(System.out::println);
        };
    }
}
