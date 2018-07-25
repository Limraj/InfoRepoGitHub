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

public class GitHubRepoPublicByUserServiceTest {

    private static UnitTestExecutor unitTestExecutor;
    private static GitHubRepoService service;
    private static Consumer<Optional<InfoRepo>> assertsNotEmpty;
    private static BiConsumer<Optional<InfoRepo>, Properties> assertsEqualsValue;

    @BeforeClass
    public static void setup() {
        TestUnitConfig  config = new TestUnitConfig();
        service = new GitHubRepoPublicByUserService(config.getRestTemplate());
        unitTestExecutor = UnitTestExecutor.builder()
                .pathToDataTest("src/test/resources/user/")
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
        unitTestExecutor.executeTestRepoIsNotEmpty("limraj-user-dicegame.properties", this::toTestGetInfoRepo, assertsNotEmpty);
        //unitTestExecutor.executeTestRepoIsNotEmpty("limraj-user-figury.properties", this::getInfoRepo, verifierIsNotEmpty);
        //unitTestExecutor.executeTestRepoIsNotEmpty("ruby-user-b.r-l.o.properties", this::getInfoRepo, verifierIsNotEmpty);
    }

    @Test
    public void testGetInfoRepo() {
        unitTestExecutor.executeTest("limraj-user-dicegame.properties", this::toTestGetInfoRepo, assertsEqualsValue);
        //unitTestExecutor.executeTest("limraj-user-figury.properties", this::getInfoRepo, verifierValue);
        //unitTestExecutor.executeTest("ruby-user-b.r-l.o.properties", this::getInfoRepo, verifierValue);
    }


    @Test
    public void testGetInfoReposIsNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("limraj-user-dicegame.properties", this::toTestGetInfoRepos, assertsNotEmpty);
        //unitTestExecutor.executeTestRepoIsNotEmpty("limraj-user-figury.properties", this::getInfoRepos, verifierIsNotEmpty);
        //unitTestExecutor.executeTestRepoIsNotEmpty("ruby-user-b.r-l.o.properties", this::getInfoRepos, verifierIsNotEmpty);
    }

    @Test
    public void testGetInfoRepos() {
        unitTestExecutor.executeTest("limraj-user-dicegame.properties", this::toTestGetInfoRepos, assertsEqualsValue);
        //unitTestExecutor.executeTest("limraj-user-figury.properties", this::getInfoRepos, verifierValue);
        //unitTestExecutor.executeTest("ruby-user-b.r-l.o.properties", this::getInfoRepos, verifierValue);
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