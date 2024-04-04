package com.epam.rd.autotasks.chesspuzzles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RegisteringChessPieces.class)
public class DefaultWhite {
    @Bean
    public String variant() {
        return "DefaultWhite";
    }
}