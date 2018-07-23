package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;

interface GitHubRepoPublicService {
    DataRepo downloadDataFor(String owner, String repoName);
}
