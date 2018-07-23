package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class GitHubRepoPublicServiceImpl implements GitHubRepoPublicService {

    private RestTemplate restTemplate;

    @Autowired
    GitHubRepoPublicServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DataRepo downloadDataFor(String owner, String repoName) {
        return restTemplate.getForObject("https://api.github.com/repos/{owner}/{repoName}", DataRepo.class, owner, repoName);
    }

}
