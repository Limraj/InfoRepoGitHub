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

    //Dlaczego wstrzykuję przez konstruktor? To pozwala uniknąć zbyt rozbudowanych klas,
    //konstruktor nie powinien przyjmować więcej niż 3 argumentów,
    //to zmusza programistę do lepszego projektowania nowych funkcjonalności,
    //np przez utworzenie nowego kontrolera;
    //jeśli wstrzykujemy do pola, to przy rozbudowywaniu kontrolera,
    //łatwo się zapomnieć;
    @Autowired
    GitHubRepoController(GitHubRepoPublicService service) {
        this.service = service;
    }

    @GetMapping("/{owner}/{repositoryName}")
    public DataRepo getDataRepo(@PathVariable String owner, @PathVariable String repositoryName) {
        return service.get(owner, repositoryName);
    }
}
