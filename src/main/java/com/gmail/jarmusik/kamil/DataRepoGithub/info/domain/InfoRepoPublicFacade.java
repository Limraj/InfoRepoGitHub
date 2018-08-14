package com.gmail.jarmusik.kamil.DataRepoGithub.info.domain;

import com.gmail.jarmusik.kamil.DataRepoGithub.infrastructure.metrics.logging.LogForBeanSpring;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

@LogForBeanSpring
public class InfoRepoPublicFacade {

    private final RestTemplate restTemplate;

    InfoRepoPublicFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InfoRepo getInfoRepo(String owner, String repoName) {
        requireNonNull(owner, repoName);
        return restTemplate.getForObject("https://api.github.com/repos/{owner}/{repoName}", InfoRepo.class, owner, repoName);
    }
}
