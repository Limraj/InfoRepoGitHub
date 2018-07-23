package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
class ObjectMapperConfig {

    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    void setup() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        mapper.setDateFormat(dateFormat);
        mapper.addMixIn(DataRepo.class, DataRepoPropertyJson.class);
        if(!mapper.isEnabled(SerializationFeature.INDENT_OUTPUT))
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
