package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.util.InfoRepoSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("ownerAsUser")
class GitHubRepoPublicByUserService implements GitHubRepoService {

    private RestTemplate restTemplate;

    @Autowired
    GitHubRepoPublicByUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public InfoRepo getInfoRepo(String user, String repoName) {
        List<InfoRepo> repos = getInfoRepos(user);
        return InfoRepoSelector.selectInfoRepo(user, repoName, repos);
    }

    @Override
    public List<InfoRepo> getInfoRepos(String user) {
        return Arrays.asList(restTemplate.getForObject("https://api.github.com/users/{user}/repos", InfoRepo[].class, user));
    }

}
