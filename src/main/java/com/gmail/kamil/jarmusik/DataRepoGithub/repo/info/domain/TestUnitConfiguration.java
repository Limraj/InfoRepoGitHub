package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;

public class TestUnitConfiguration {

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public TestUnitConfiguration() {

        InfoRepoConfiguration configuration =  new InfoRepoConfiguration();
        objectMapper = configuration.objectMapper();
        restTemplate = configuration.restTemplate(new RestTemplateBuilder(), objectMapper);
    }

    public InfoRepoPublicByOrganizationFacade infoRepoPublicByOrganizationFacade() {
        return new InfoRepoPublicByOrganizationFacade(restTemplate);
    }

    public InfoRepoPublicByUserFacade infoRepoPublicByUserFacade() {
        return new InfoRepoPublicByUserFacade(restTemplate);
    }

    public DateFormat getDateFormat() {
        return objectMapper.getDateFormat();
    }


}
