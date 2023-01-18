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
        LocalDate localDate = LocalDate.of(2007,3,1);


        return args -> {
            log.info("Preloading " + personRepo.save(new Person(localDate,"jason",20)));
            log.info("Preloading " + personRepo.save(new Person(localDate,"jason",30)));
            log.info("Preloading " + personRepo.save(new Person(localDate,"mason",45)));
            log.info("Preloading " + personRepo.save(new Person(localDate,"lason",20)));


        };
    }
}
