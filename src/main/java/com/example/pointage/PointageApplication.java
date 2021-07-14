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

            pointageRepository.save(new Pointage(null,"BDAEODKDO","P2","T12",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"IN21313&","P3","T11",new Date(),"Invitation",false));
            pointageRepository.save(new Pointage(null,"BD42434325","P2","T1",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"TK43432a","P3","T1",new Date(),"Ticket",true));
            pointageRepository.save(new Pointage(null,"BDAEODKDaO","P2","T12",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"IN21313a&","P3","T11",new Date(),"Invitation",false));
            pointageRepository.save(new Pointage(null,"BD42434a325","P2","T1",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"TK4a3432","P3","T1",new Date(),"Ticket",true));
            pointageRepository.save(new Pointage(null,"BaDAEODKDO","P2","T12",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"IN213a13&","P3","T11",new Date(),"Invitation",false));
            pointageRepository.save(new Pointage(null,"BD424a34325","P2","T1",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"TKa43432","P3","T1",new Date(),"Ticket",true));
            pointageRepository.save(new Pointage(null,"BDAEaODKDO","P2","T12",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"IN21a313&","P3","T11",new Date(),"Invitation",false));
            pointageRepository.save(new Pointage(null,"BaD42434325","P2","T1",new Date(),"Badge",true));
            pointageRepository.save(new Pointage(null,"TKa43432","P3","T1",new Date(),"Ticket",true));
            pointageRepository.findAll().forEach(System.out::println);
        };
    }
}
