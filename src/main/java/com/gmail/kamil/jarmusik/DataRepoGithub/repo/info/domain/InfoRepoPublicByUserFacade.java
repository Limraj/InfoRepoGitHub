package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.gmail.kamil.jarmusik.DataRepoGithub.infrastructure.metrics.logging.LogForBeanSpring;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

@LogForBeanSpring
public class InfoRepoPublicByUserFacade extends InfoRepoPublic {

    public InfoRepoPublicByUserFacade(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public List<InfoRepo> getInfoRepos(String user) {
        requireNonNull(user);
        return Arrays.asList(restTemplate.getForObject("https://api.github.com/users/{user}/repos", InfoRepo[].class, user));
    }

}
