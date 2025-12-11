package com.platform.talent.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TalentPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentPoolApplication.class, args);
    }
}

