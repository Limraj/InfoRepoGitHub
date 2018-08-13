package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;

public class TestUnitConfiguration {

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private InfoRepoConfiguration configuration;

    public TestUnitConfiguration() {

        configuration =  new InfoRepoConfiguration();
        objectMapper = configuration.objectMapper();
        restTemplate = configuration.restTemplate(new RestTemplateBuilder(), objectMapper);
    }

    public InfoRepoPublicByOrganizationFacade infoRepoPublicByOrganizationFacade() {
        return configuration.infoRepoPublicByOrganizationFacade(restTemplate);
    }

    public InfoRepoPublicByUserFacade infoRepoPublicByUserFacade() {
        return configuration.infoRepoPublicByUserFacade(restTemplate);
    }

    public DateFormat getDateFormat() {
        return objectMapper.getDateFormat();
    }


}
