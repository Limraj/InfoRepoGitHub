package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info;

import com.gmail.kamil.jarmusik.DataRepoGithub.infrastructure.metrics.logging.LogForBeanSpring;
import com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain.InfoRepoPublic;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.logging.Level;

@Log
@RestController
@LogForBeanSpring
@RequestMapping("/repositories")
public class InfoRepoController {

    private InfoRepoPublic orgFacade;
    private InfoRepoPublic userFacade;
    //wstrzykuję przez konstruktor, to pomaga utrzymać porządek w klasie,
    //konstruktor nie powinien przyjmować więcej niż 3 argumentów,
    //oraz można ją utworzyć poza kontekstem Springowym i nie wykorzystując refleksji;
    @Autowired
    public InfoRepoController(@Qualifier("byOrganization")InfoRepoPublic orgFacade,
                              @Qualifier("byUser")InfoRepoPublic userFacade) {
        this.orgFacade = orgFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/{owner}/{repoName}")
    @Cacheable("infoRepo")
    @CacheEvict(value="infoRepo", allEntries=true)
    public InfoRepo getInfoRepo(@PathVariable String owner, @PathVariable String repoName) {
        return orgFacade.getInfoRepo(owner, repoName);
    }

    @GetMapping("/{owner}")
    @Cacheable("infoRepo")
    @CacheEvict(value="infoRepo", allEntries=true)
    public List<InfoRepo> getInfoRepos(@PathVariable String owner) {
        try {
            return orgFacade.getInfoRepos(owner);
        } catch (HttpClientErrorException ex) {
            log.log(Level.WARNING, ex.getMessage());
        }
        return userFacade.getInfoRepos(owner);
    }

}
