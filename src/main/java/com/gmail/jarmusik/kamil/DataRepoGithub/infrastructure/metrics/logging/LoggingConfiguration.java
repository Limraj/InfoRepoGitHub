package com.gmail.jarmusik.kamil.DataRepoGithub.infrastructure.metrics.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoggingConfiguration {
    @Bean
    LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
