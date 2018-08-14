package com.gmail.jarmusik.kamil.DataRepoGithub.info;

import com.gmail.jarmusik.kamil.DataRepoGithub.info.domain.InfoRepo;
import com.gmail.jarmusik.kamil.DataRepoGithub.info.domain.InfoRepoPublicFacade;
import com.gmail.jarmusik.kamil.DataRepoGithub.infrastructure.metrics.logging.LogForBeanSpring;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@LogForBeanSpring
@RequestMapping("/repositories")
public class InfoRepoPublicController {

    private final InfoRepoPublicFacade sourceInfoRepo;

    public InfoRepoPublicController(InfoRepoPublicFacade sourceInfoRepo) {
        this.sourceInfoRepo = sourceInfoRepo;
    }

    @GetMapping("/{owner}/{repoName}")
    @Cacheable("infoRepo")
    @CacheEvict(value="infoRepo", allEntries=true)
    public InfoRepo getInfoRepo(@PathVariable String owner, @PathVariable String repoName) {
        return sourceInfoRepo.getInfoRepo(owner, repoName);
    }
}
