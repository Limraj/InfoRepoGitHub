package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class GitHubRepoController {

    private GitHubRepoPublicService service;

    //wstrzykuję przez konstruktor, to pomaga utrzymać porządek w klasie,
    //konstruktor nie powinien przyjmować więcej niż 3 argumentów,
    //oraz można ją utworzyć poza kontekstem Springowym;
    @Autowired
    GitHubRepoController(GitHubRepoPublicService service) {
        this.service = service;
    }

    @GetMapping("/{owner}/{repositoryName}")
    public DataRepo getDataRepo(@PathVariable String owner, @PathVariable String repositoryName) {
        return service.downloadDataFor(owner, repositoryName);
    }
}
