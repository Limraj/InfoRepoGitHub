package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GitHubRepoPublicForOrganizationServiceTest {

    private static GitHubRepoPublicForOrganizationService service;
    private static TestUnitConfig config;
    private static Properties rubyRuby;

    @BeforeClass
    public static void setup() {
        config = new TestUnitConfig();
        service = new GitHubRepoPublicForOrganizationServiceImpl(config.getRestTemplate());
        rubyRuby = TestUnitConfig.loadProperties("src/test/resources/data-test-ruby-organization-ruby.properties");
    }

    @Test
    public void testGetInfoRepoForOrganizationIsNotEmpty() {
        //given:
        String owner = rubyRuby.getProperty("github.owner");
        String repoName = rubyRuby.getProperty("github.repoName");
        //when:
        InfoRepo infoRepo = service.getInfoRepoForOrganization(owner,repoName);
        //then:
        assertTrue(isNotBlank(infoRepo.getCloneUrl()));
        assertTrue(isNotBlank(infoRepo.getDescription()));
        assertTrue(isNotBlank(infoRepo.getFullName()));
        assertTrue(infoRepo.getCreatedAt() != null);
        assertTrue(infoRepo.getStars() > 0);

    }

    @Test
    public void testGetInfoRepoForOrganization() {
        //given:
        String owner = rubyRuby.getProperty("github.owner");
        String repoName = rubyRuby.getProperty("github.repoName");
        String fullName = rubyRuby.getProperty("github.repo.fullName");
        String createAt = rubyRuby.getProperty("github.repo.createAt");
        String cloneUrl = rubyRuby.getProperty("github.repo.cloneUrl");
        //when:
        InfoRepo infoRepo = service.getInfoRepoForOrganization(owner,repoName);
        //then:
        assertEquals(fullName, infoRepo.getFullName());
        assertEquals(createAt, config.dateToString(infoRepo.getCreatedAt()));
        assertEquals(cloneUrl, infoRepo.getCloneUrl());

    }

    @Test
    public void testGetInfoReposForOrganizationIsNotEmpty() {
        //given:
        String owner = rubyRuby.getProperty("github.owner");
        //when:
        List<InfoRepo> infoRepos = service.getInfoReposForOrganization(owner);
        //then:
        assertTrue(infoRepos != null);
        assertTrue(!infoRepos.isEmpty());
    }

    @Test
    public void testGetInfoReposForOrganizationForBR_LO() {
        //given:
        Properties rubyBR_LO = TestUnitConfig.loadProperties("src/test/resources/data-test-ruby-organization-b.r-l.o.properties");
        String owner = rubyBR_LO.getProperty("github.owner");
        String fullName = rubyBR_LO.getProperty("github.repo.fullName");
        String createAt = rubyBR_LO.getProperty("github.repo.createAt");
        String cloneUrl = rubyBR_LO.getProperty("github.repo.cloneUrl");
        //when:
        List<InfoRepo> infoRepos = service.getInfoReposForOrganization(owner);
        Optional<InfoRepo> infoRepo = getDataInfoForFullName(fullName, infoRepos);

        //then:
        assertTrue(infoRepo.isPresent());
        assertEquals(fullName, infoRepo.get().getFullName());
        assertEquals(createAt, config.dateToString(infoRepo.get().getCreatedAt()));
        assertEquals(cloneUrl, infoRepo.get().getCloneUrl());

    }

    @Test
    public void testGetInfoReposForOrganizationForRubySpecIO() {
        //given:
        Properties rubyRubySpecIO = TestUnitConfig.loadProperties("src/test/resources/data-test-ruby-organization-rubyspec.github.io.properties");
        String owner = rubyRubySpecIO.getProperty("github.owner");
        String fullName = rubyRubySpecIO.getProperty("github.repo.fullName");
        String createAt = rubyRubySpecIO.getProperty("github.repo.createAt");
        String cloneUrl = rubyRubySpecIO.getProperty("github.repo.cloneUrl");
        //when:
        List<InfoRepo> infoRepos = service.getInfoReposForOrganization(owner);
        Optional<InfoRepo> infoRepo = getDataInfoForFullName(fullName, infoRepos);

        //then:
        assertTrue(infoRepo.isPresent());
        assertEquals(fullName, infoRepo.get().getFullName());
        assertEquals(createAt, config.dateToString(infoRepo.get().getCreatedAt()));
        assertEquals(cloneUrl, infoRepo.get().getCloneUrl());

    }

    private Optional<InfoRepo> getDataInfoForFullName(String fullName, List<InfoRepo> repos) {
        return repos.stream()
                .filter(a -> a.getFullName().equals(fullName))
                .findAny();
    }

}