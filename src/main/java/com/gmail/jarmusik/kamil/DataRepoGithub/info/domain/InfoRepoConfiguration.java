package com.gmail.jarmusik.kamil.DataRepoGithub.info.domain;

import com.fasterxml.jackson.databind.*;
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(isoSameDate());
        objectMapper.addMixIn(InfoRepo.class, InfoRepoPropertyJson.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(!objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT))
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

    @Bean
    ObjectMapper objectMapperDeserialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(isoSameDate());
        objectMapper.addMixIn(InfoRepo.class, InfoRepoPropertyJsonDeserialize.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
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
    InfoRepoPublicFacade infoRepoPublicFacade(RestTemplate restTemplate) {
        return new InfoRepoPublicFacade(restTemplate);
    }

    private static DateFormat isoSameDate() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
