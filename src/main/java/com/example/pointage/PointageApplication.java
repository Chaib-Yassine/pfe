package com.example.pointage;

import com.example.pointage.Repository.PointageRepository;
import com.example.pointage.entites.Pointage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class PointageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointageApplication.class, args);
    }
    @Bean
    CommandLineRunner start(PointageRepository pointageRepository, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Pointage.class);
        return args -> {

            pointageRepository.save(new Pointage(null,"AEODKDO","P2","T12",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"21313&","P3","T11",new Date(),"Invitation",false));
            pointageRepository.save(new Pointage(null,"42434325","P2","T1",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"43432","P3","T1",new Date(),"Ticket",true));
            pointageRepository.findAll().forEach(System.out::println);
        };
    }
}
