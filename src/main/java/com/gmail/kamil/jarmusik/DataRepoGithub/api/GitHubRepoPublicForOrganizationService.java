package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.List;

interface GitHubRepoPublicForOrganizationService {
    InfoRepo getInfoRepoForOrganization(String organization, String repoName);
    List<InfoRepo> getInfoReposForOrganization(String organization);
}
