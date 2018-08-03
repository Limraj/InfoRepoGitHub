package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({ "fullName", "description", "cloneUrl", "stars", "createdAt" })
interface InfoRepoPropertyJson {

    @JsonProperty("fullName")
    String getFullName();

    @JsonProperty("full_name")
    void setFullName(String fullName);

    @JsonProperty("cloneUrl")
    String getCloneUrl();

    @JsonProperty("git_url")
    void setCloneUrl(String cloneUrl);

    @JsonProperty("createdAt")
    Date getCreatedAt();

    @JsonProperty("created_at")
    void setCreatedAt(Date createdAt);

    @JsonProperty("stars")
    int getStars();

    @JsonProperty("stargazers_count")
    void setStars(int stars);
}
