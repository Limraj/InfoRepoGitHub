package com.gmail.kamil.jarmusik.DataRepoGithub.util;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InfoRepoSelector {

    private static final Logger logger = Logger.getLogger(InfoRepoSelector.class.getName());

    public static List<InfoRepo> resultSelectInfoRepoByFullName(String fullName, List<InfoRepo> repos) {
        return repos.stream()
                .filter(a -> a.getFullName().equals(fullName))
                .collect(Collectors.toList());
    }

    public static InfoRepo selectInfoRepo(String owner, String repoName, List<InfoRepo> repos) {
        List<InfoRepo> result = InfoRepoSelector.resultSelectInfoRepoByFullName(createFullName(owner, repoName), repos);
        if(result.isEmpty())
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        if(result.size() > 1)
            logger.log(Level.WARNING, "mutli result:" + result);
        return result.get(0);
    }

    public static String createFullName(String owner, String repoName) {
        return owner + "/" + repoName;
    }
}
