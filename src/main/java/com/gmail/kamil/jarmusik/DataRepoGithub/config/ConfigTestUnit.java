package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class ConfigTestUnit {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public ConfigTestUnit() {
        objectMapper = new ObjectMapperConfig().objectMapper();
        restTemplate = new RestTemplateConfig().restTemplate(new RestTemplateBuilder(), objectMapper);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String fromatDateToString(Date date) {
        return objectMapper.getDateFormat().format(date);
    }
}
