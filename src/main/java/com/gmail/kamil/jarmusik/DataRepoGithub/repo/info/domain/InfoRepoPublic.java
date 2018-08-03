package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

public abstract class InfoRepoPublic {

    protected RestTemplate restTemplate;

    InfoRepoPublic(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InfoRepo getInfoRepo(String organization, String repoName) {
        requireNonNull(organization, repoName);
        return restTemplate.getForObject("https://api.github.com/repos/{organization}/{repoName}", InfoRepo.class, organization, repoName);
    }
}
