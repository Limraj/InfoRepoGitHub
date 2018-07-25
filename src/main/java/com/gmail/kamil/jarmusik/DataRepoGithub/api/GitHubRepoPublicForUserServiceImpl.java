package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class GitHubRepoPublicForUserServiceImpl implements GitHubRepoPublicForUserService {

    private RestTemplate restTemplate;

    @Autowired
    GitHubRepoPublicForUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public InfoRepo getInfoRepoForUser(String user, String repoName) {
        return restTemplate.getForObject("https://api.github.com/repos/{owner}/{repoName}", InfoRepo.class, user, repoName);
    }

    @Override
    public List<InfoRepo> getInfoReposForUser(String user) {
        return Arrays.asList(restTemplate.getForObject("https://api.github.com/users/{user}/repos", InfoRepo[].class, user));
    }

}
