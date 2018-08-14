package com.gmail.jarmusik.kamil.DataRepoGithub.info.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class TestUnitConfiguration {

    private static final TestUnitConfiguration INSTANCE = new TestUnitConfiguration();
    private final ObjectMapper objectMapperDeserialize;
    private final InfoRepoPublicFacade infoRepoPublicFacade;

    private TestUnitConfiguration() {
        InfoRepoConfiguration conf = new InfoRepoConfiguration();
        objectMapperDeserialize = conf.objectMapperDeserialize();
        RestTemplate restTemplate = conf.restTemplate(new RestTemplateBuilder(), conf.objectMapper());
        infoRepoPublicFacade = conf.infoRepoPublicFacade(restTemplate);
    }

    public static TestUnitConfiguration getInstance() {
        return INSTANCE;
    }

    public InfoRepoPublicFacade infoRepoPublicFacade() {
        return infoRepoPublicFacade;
    }

    public ObjectMapper objectMapperDeserialize() {
        return objectMapperDeserialize;
    }

}
