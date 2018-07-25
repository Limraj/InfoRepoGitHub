package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.List;

interface GitHubRepoService {
    InfoRepo getInfoRepo(String owner, String repoName);
    List<InfoRepo> getInfoRepos(String owner);
}
