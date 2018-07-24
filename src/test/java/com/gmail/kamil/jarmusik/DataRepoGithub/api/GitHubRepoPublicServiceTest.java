package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.ConfigTestUnit;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.DataRepo;
import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.Properties;

@TestPropertySource(locations="classpath:test.properties")
public class GitHubRepoPublicServiceTest {

    private static GitHubRepoPublicService service;
    private static String owner;
    private static String repoName;
    private static String fullName;
    private static String createAt;
    private static String cloneUrl;

    private static ConfigTestUnit config;

    @BeforeClass
    public static void setup() throws IOException {
        config = new ConfigTestUnit();
        service = new GitHubRepoPublicServiceImpl(config.getRestTemplate());
        Properties properties = ConfigTestUnit.loadProperties("src/test/resources/test.properties");
        owner = properties.getProperty("github.owner");
        repoName = properties.getProperty("github.repoName");
        fullName = properties.getProperty("github.repo.fullName");
        createAt = properties.getProperty("github.repo.createAt");
        cloneUrl = properties.getProperty("github.repo.cloneUrl");
    }

    @Test
    public void testDownloadDataRepoIsNotEmpty() {
        //when:
        DataRepo dataRepo = service.downloadDataFor(owner,repoName);
        //then:
        Assert.assertEquals(true, Strings.isNotBlank(dataRepo.getCloneUrl()));
        Assert.assertEquals(true, Strings.isNotBlank(dataRepo.getDescription()));
        Assert.assertEquals(true, Strings.isNotBlank(dataRepo.getFullName()));
        Assert.assertEquals(true, dataRepo.getCreatedAt() != null);
        Assert.assertEquals(true, dataRepo.getStars() > 0);

    }

    @Test
    public void testDownloadDataRepo() {
        //when:
        DataRepo dataRepo = service.downloadDataFor(owner,repoName);
        //then:
        Assert.assertEquals(fullName, dataRepo.getFullName());
        Assert.assertEquals(createAt, config.dateToString(dataRepo.getCreatedAt()));
        Assert.assertEquals(cloneUrl, dataRepo.getCloneUrl());

    }

}