package com.example3.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bsoto
 * @project demo3
 * @created at 18-01-2023
 */
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase (PersonRepo personRepo){

        personRepo.deleteAll();
        LocalDate localDate = LocalDate.of(2007,3,1);
        List<String> hobbies1  = List.of("futbol","natacion");
        List<String> hobbies2  = List.of("futbol","tenis");
        List<String> hobbies3  = List.of("tenis","canto");
        List<String> hobbies4  = List.of("baile","actuacion");

        return args -> {
            log.info("Preloading " + personRepo.save(new Person(localDate,"jason",20,hobbies1,"a")));
            log.info("Preloading " + personRepo.save(new Person(localDate,"jason",30,hobbies2,"e")));
            log.info("Preloading " + personRepo.save(new Person(localDate,"mason",45,hobbies3,"i")));
            log.info("Preloading " + personRepo.save(new Person(localDate,"lason",20,hobbies4,"o")));


        };
    }
}
