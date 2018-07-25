package com.gmail.kamil.jarmusik.DataRepoGithub.util;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.List;
import java.util.Optional;

public class InfoRepoUtil {

    public static Optional<InfoRepo> selectInfoRepoByFullName(String fullName, List<InfoRepo> repos) {
        return repos.stream()
                .filter(a -> a.getFullName().equals(fullName))
                .findAny();
    }

    public static String createFullName(String owner, String repoName) {
        return owner + "/" + repoName;
    }
}
