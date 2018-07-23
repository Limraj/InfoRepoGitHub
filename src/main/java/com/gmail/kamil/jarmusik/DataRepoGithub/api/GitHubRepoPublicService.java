package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class GitHubRepoPublicService {

    private RestTemplate restTemplate;

    @Autowired
    GitHubRepoPublicService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    DataRepo get(String owner, String repoName) {
        return restTemplate.getForObject("https://api.github.com/repos/{owner}/{repoName}", DataRepo.class, owner, repoName);
    }

}
