package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class ObjectMapperConfig {

    @Bean
    @Primary
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(DateFormatFactory.isoSameDate());
        mapper.addMixIn(InfoRepo.class, InfoRepoPropertyJson.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(!mapper.isEnabled(SerializationFeature.INDENT_OUTPUT))
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
