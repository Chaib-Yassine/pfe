package org.inti;

import org.inti.Repository.PointageRepository;
import org.inti.entites.Pointage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients

public class PointageStreamP2Application {

    public static void main(String[] args) {
        SpringApplication.run(PointageStreamP2Application.class, args);
    }
    @Bean
    CommandLineRunner start(PointageRepository pointageRepository) {
       //restConfiguration.exposeIdsFor(Pointage.class);
        return args -> {
            System.out.println("$$$$$$$$$$ entre $$$$$$$$$$");
            /// exemple
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //////////
            DateTimeFormatter toFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd HH:mm:ss")
                    .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 1, false)
                    .toFormatter();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt2 = LocalDateTime.parse(sdf.format(dt), formatter);
            System.out.println(toFormatter.format(ldt2));

            ///////////
          /*  pointageRepository.save(new Pointage(null,"BDAEODKDO","P2","T12",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"IN21313&","P3","T11",ldt2,"Invitation",false)).subscribe();
            pointageRepository.save(new Pointage(null,"BD42434325","P2","T1",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"TK43432a","P3","T1",ldt2,"Ticket",true)).subscribe();
            pointageRepository.save(new Pointage(null,"BDAEODKDaO","P2","T12",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"IN21313a&","P3","T11",ldt2,"Invitation",false)).subscribe();
            pointageRepository.save(new Pointage(null,"BD42434a325","P2","T1",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"TK4a3432","P3","T1",ldt2,"Ticket",true)).subscribe();
            pointageRepository.save(new Pointage(null,"BaDAEODKDO","P2","T12",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"IN213a13&","P3","T11",ldt2,"Invitation",false)).subscribe();
            pointageRepository.save(new Pointage(null,"BD424a34325","P2","T1",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"TKa43432","P3","T1",ldt2,"Ticket",true)).subscribe();
            pointageRepository.save(new Pointage(null,"BDAEaODKDO","P2","T12",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"IN21a313&","P3","T11",ldt2,"Invitation",false)).subscribe();
            pointageRepository.save(new Pointage(null,"BaD42434325","P2","T1",ldt2,"Badge",true)).subscribe();
            pointageRepository.save(new Pointage(null,"TKa43432","P3","T1",ldt2,"Ticket",true)).subscribe();*/
            pointageRepository.findAll().subscribe(System.out::println);
          //  pointageRepository.findAll()
        };
    }
}
