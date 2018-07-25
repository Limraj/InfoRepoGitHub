package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class GitHubRepoController {

    private GitHubRepoPublicForOrganizationService service;

    //wstrzykuję przez konstruktor, to pomaga utrzymać porządek w klasie,
    //konstruktor nie powinien przyjmować więcej niż 3 argumentów,
    //oraz można ją utworzyć poza kontekstem Springowym;
    @Autowired
    GitHubRepoController(GitHubRepoPublicForOrganizationService service) {
        this.service = service;
    }

    @GetMapping("/{organization}/{repoName}")
    //@Cacheable("infoRepo")
    //@CacheEvict(value="infoRepo", allEntries=true)
    public InfoRepo getInfoRepoForOrganization(@PathVariable String organization, @PathVariable String repoName) {
        InfoRepo inforRepo = service.getInfoRepoForOrganization(organization, repoName);
        System.out.println("infoRepo: " + inforRepo);
        return service.getInfoRepoForOrganization(organization, repoName);
    }

}
