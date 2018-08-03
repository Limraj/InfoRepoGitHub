package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;

public class TestUnitConfiguration {

    InfoRepoConfiguration configuration;

    public TestUnitConfiguration() {
        this.configuration =  new InfoRepoConfiguration();
    }

    public InfoRepoPublicByOrganizationFacade infoRepoPublicByOrganizationFacade() {
        ObjectMapper objectMapper = configuration.objectMapper();
        RestTemplate restTemplate = configuration.restTemplate(new RestTemplateBuilder(), objectMapper);
        return new InfoRepoPublicByOrganizationFacade(restTemplate);
    }

    public InfoRepoPublicByUserFacade infoRepoPublicByUserFacade() {
        ObjectMapper objectMapper = configuration.objectMapper();
        RestTemplate restTemplate = configuration.restTemplate(new RestTemplateBuilder(), objectMapper);
        return new InfoRepoPublicByUserFacade(restTemplate);
    }

    public DateFormat getDateFormat() {
        return configuration.objectMapper().getDateFormat();
    }


}
