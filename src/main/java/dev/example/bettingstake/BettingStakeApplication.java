package dev.example.bettingstake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BettingStakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BettingStakeApplication.class, args);
    }

}
