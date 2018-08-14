package com.gmail.jarmusik.kamil.DataRepoGithub.info.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface InfoRepoPropertyJsonDeserialize {

    @JsonProperty("full_name")
    String getFullName();

    @JsonProperty("fullName")
    void setFullName(String fullName);

    @JsonProperty("git_url")
    String getCloneUrl();

    @JsonProperty("cloneUrl")
    void setCloneUrl(String cloneUrl);

    @JsonProperty("created_at")
    Date getCreatedAt();

    @JsonProperty("createdAt")
    void setCreatedAt(Date createdAt);

    @JsonProperty("stargazers_count")
    int getStars();

    @JsonProperty("stars")
    void setStars(int stars);
}
