package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class TestUnitConfig {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public TestUnitConfig() {
        objectMapper = new ObjectMapperConfig().objectMapper();
        restTemplate = new RestTemplateConfig().restTemplate(new RestTemplateBuilder(), objectMapper);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String dateToString(Date date) {
        return objectMapper.getDateFormat().format(date);
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
