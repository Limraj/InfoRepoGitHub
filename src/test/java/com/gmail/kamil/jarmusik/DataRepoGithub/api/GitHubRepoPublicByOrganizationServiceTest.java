package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.util.InfoRepoUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import tool.UnitTestExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GitHubRepoPublicByOrganizationServiceTest {

    private static UnitTestExecutor unitTestExecutor;
    private static GitHubRepoService service;
    private static TestUnitConfig config;
    private static Consumer<Optional<InfoRepo>> assertsNotEmpty;
    private static BiConsumer<Optional<InfoRepo>, Properties> assertsEqualsValue;

    @BeforeClass
    public static void setup() {
        config = new TestUnitConfig();
        service = new GitHubRepoPublicByOrganizationService(config.getRestTemplate());
        unitTestExecutor = UnitTestExecutor.builder()
                .pathToDataTest("src/test/resources/organization/")
                .build();

        assertsNotEmpty = infoRepo -> {
            assertTrue(infoRepo.isPresent());
            assertTrue(isNotBlank(infoRepo.get().getCloneUrl()));
            assertTrue(isNotBlank(infoRepo.get().getDescription()));
            assertTrue(isNotBlank(infoRepo.get().getFullName()));
            assertNotNull(infoRepo.get().getCreatedAt());
        };

        assertsEqualsValue = new BiConsumer<Optional<InfoRepo>, Properties>() {
            TestUnitConfig config;

            public BiConsumer<Optional<InfoRepo>, Properties> setConfig(TestUnitConfig config) {
                this.config = config;
                return this;
            }

            @Override
            public void accept(Optional<InfoRepo> infoRepo, Properties dataTest) {
                String fullName = dataTest.getProperty("github.repo.fullName");
                String createAt = dataTest.getProperty("github.repo.createAt");
                String cloneUrl = dataTest.getProperty("github.repo.cloneUrl");
                assertTrue(infoRepo.isPresent());
                assertEquals(fullName, infoRepo.get().getFullName());
                assertEquals(createAt, config.dateToString(infoRepo.get().getCreatedAt()));
                assertEquals(cloneUrl, infoRepo.get().getCloneUrl());
            }
        }.setConfig(config);
    }

    @Test
    public void testGetInfoRepoIsNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-ruby.properties", this::toTestGetInfoRepo, assertsNotEmpty);
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-rubyspec.github.io.properties", this::toTestGetInfoRepo, assertsNotEmpty);
    }

    @Test
    public void testGetInfoRepo() {
        unitTestExecutor.executeTest("ruby-organization-ruby.properties", this::toTestGetInfoRepo, assertsEqualsValue);
        unitTestExecutor.executeTest("ruby-organization-rubyspec.github.io.properties", this::toTestGetInfoRepo, assertsEqualsValue);
    }

    @Test
    public void testGetInfoReposIsNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-ruby.properties", this::toTestGetInfoRepos, assertsNotEmpty);
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-rubyspec.github.io.properties", this::toTestGetInfoRepos, assertsNotEmpty);
    }

    @Test
    public void testGetInfoRepos() {
        unitTestExecutor.executeTest("ruby-organization-ruby.properties", this::toTestGetInfoRepos, assertsEqualsValue);
        unitTestExecutor.executeTest("ruby-organization-rubyspec.github.io.properties", this::toTestGetInfoRepos, assertsEqualsValue);
    }

    private Optional<InfoRepo> toTestGetInfoRepos(String owner, String repoName) {
        List<InfoRepo> infoRepos = service.getInfoRepos(owner);
        return InfoRepoUtil.selectInfoRepoByFullName(InfoRepoUtil.createFullName(owner, repoName), infoRepos);
    }

    private Optional<InfoRepo> toTestGetInfoRepo(String owner, String repoName) {
        InfoRepo infoRepo = service.getInfoRepo(owner, repoName);
        return Optional.of(infoRepo);
    }

}