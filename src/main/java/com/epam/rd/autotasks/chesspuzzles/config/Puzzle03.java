package com.epam.rd.autotasks.chesspuzzles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RegisteringChessPieces.class)
public class Puzzle03 {
    @Bean
    public String variant() {
        return "Puzzle03";
    }
}
