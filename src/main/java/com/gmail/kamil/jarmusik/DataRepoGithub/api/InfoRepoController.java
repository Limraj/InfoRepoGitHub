package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class InfoRepoController {

    private InfoRepoService orgService;
    private InfoRepoService userService;
    //wstrzykuję przez konstruktor, to pomaga utrzymać porządek w klasie,
    //konstruktor nie powinien przyjmować więcej niż 3 argumentów,
    //oraz można ją utworzyć poza kontekstem Springowym i nie wykorzystując refleksji;
    @Autowired
    InfoRepoController(@Qualifier("ownerAsOrganization") InfoRepoService orgService,
                       @Qualifier("ownerAsUser") InfoRepoService userService) {
        this.orgService = orgService;
        this.userService = userService;
    }

    @GetMapping("/{owner}/{repoName}")
    @Cacheable("infoRepo")
    @CacheEvict(value="infoRepo", allEntries=true)
    public InfoRepo getInfoRepoByOrganization(@PathVariable String owner, @PathVariable String repoName) {
        return orgService.getInfoRepo(owner, repoName);
    }

    @GetMapping("/user/{owner}/{repoName}")
    @Cacheable("infoRepo")
    @CacheEvict(value="infoRepo", allEntries=true)
    public InfoRepo getInfoRepoByUser(@PathVariable String owner, @PathVariable String repoName) {
        return userService.getInfoRepo(owner, repoName);
    }

}
