package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.List;

public interface GitHubRepoPublicForUserService {
    InfoRepo getInfoRepoForUser(String user, String repoName);
    List<InfoRepo> getInfoReposForUser(String user);
}
