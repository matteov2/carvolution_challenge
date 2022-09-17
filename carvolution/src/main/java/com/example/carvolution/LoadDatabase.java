package com.example.carvolution;

import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.repository.AdressRepository;
import com.example.carvolution.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AdressRepository adressRepository, UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new User("Max", "Muster", adressRepository.save(new Adress("Musterstrasse 29", "3000", "Bern")))));
            log.info("Preloading " + userRepository.save(new User("Hans", "Meier", adressRepository.save(new Adress("Musterstrasse 29", "8000", "Zürich")))));
            log.info("Preloading " + userRepository.save(new User("Jan", "Müller", adressRepository.save(new Adress("Abcstrasse 6", "3086", "Belp" )))));
            log.info("Preloading " + userRepository.save(new User("Stefan", "Schneider", adressRepository.save(new Adress("Teststrasse 2", "2500", "Biel")))));
            log.info("Preloading " + userRepository.save(new User("Thomas", "Berger", adressRepository.save(new Adress("Spielstrasse 9", "3054", "Schüpfen")))));
            log.info("Preloading " + userRepository.save(new User("Fritz", "Wirth", adressRepository.save(new Adress("Xyzstrasse 4", "5000", "Basel")))));
        };
    }
}

