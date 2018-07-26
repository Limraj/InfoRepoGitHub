package com.gmail.kamil.jarmusik.DataRepoGithub.api;


import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("ownerAsOrganization")
class InfoRepoPublicByOrganizationService implements InfoRepoService {

    private RestTemplate restTemplate;

    @Autowired
    InfoRepoPublicByOrganizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public InfoRepo getInfoRepo(String organization, String repoName) {
        return restTemplate.getForObject("https://api.github.com/repos/{organization}/{repoName}", InfoRepo.class, organization, repoName);
    }

    @Override
    public List<InfoRepo> getInfoRepos(String organization) {
        return Arrays.asList(restTemplate.getForObject("https://api.github.com/orgs/{organization}/repos", InfoRepo[].class, organization));
    }

}
