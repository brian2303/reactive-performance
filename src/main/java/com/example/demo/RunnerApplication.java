package com.example.demo;

import com.example.demo.service.PlayerService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Mono;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableAsync
public class RunnerApplication {

    @Autowired
    PlayerService playerService;

    //@Bean
    CommandLineRunner demo() {
        return args -> {
            var uri =  CsvUtilFile.class.getClassLoader().getResource("data.csv");
            try (CSVReader reader = new CSVReader(new FileReader(uri.getFile()))) {
                List<String[]> registers = reader.readAll();
                registers.forEach(strings ->{
                    playerService.save(Mono.just(new Player(
                            Integer.parseInt(strings[0].trim()),
                            strings[1],
                            Integer.parseInt(Optional.of(strings[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
                            strings[3],
                            strings[4],
                            Integer.parseInt(strings[5].trim()),
                            Integer.parseInt(strings[6].trim()),
                            strings[7]
                    )));
                });

            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }
}
