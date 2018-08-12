package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
class InfoRepoConfiguration {

    @Bean
    @Primary
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(isoSameDate());
        mapper.addMixIn(InfoRepo.class, InfoRepoPropertyJson.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(!mapper.isEnabled(SerializationFeature.INDENT_OUTPUT))
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(converter);
        return builder.setConnectTimeout(3000).additionalMessageConverters(converters).build();
    }

    @Bean
    InfoRepoPublicByOrganizationFacade infoRepoPublicByOrganizationFacade(RestTemplate restTemplate) {
        return new InfoRepoPublicByOrganizationFacade(restTemplate);
    }

    @Bean
    InfoRepoPublicByUserFacade infoRepoPublicByUserFacade(RestTemplate restTemplate) {
        return new InfoRepoPublicByUserFacade(restTemplate);
    }

    private static DateFormat iso() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
    private static DateFormat isoSameDate() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
