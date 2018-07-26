package com.gmail.kamil.jarmusik.DataRepoGithub.api;


import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import tool.TestSetup;
import tool.UnitTestExecutor;
import tool.subject.GetInfoRepo;
import tool.subject.GetInfoRepos;
import tool.subject.SubjectTestGetInfo;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GitHubRepoPublicByOrganizationServiceTest {

    private static UnitTestExecutor unitTestExecutor;
    private static GitHubRepoService service;
    private static TestUnitConfig config;
    private static Consumer<InfoRepo> assertsNotEmpty;
    private static BiConsumer<InfoRepo, Properties> assertsEqualsValue;
    private static SubjectTestGetInfo subjectTestGetInfoRepos;
    private static SubjectTestGetInfo subjectTestGetInfoRepo;

    @BeforeClass
    public static void setup() {
        config = new TestUnitConfig();
        service = new GitHubRepoPublicByOrganizationService(config.getRestTemplate());
        unitTestExecutor = new UnitTestExecutor("src/test/resources/service/");
        assertsNotEmpty = TestSetup.assertsNotEmpty();
        assertsEqualsValue = TestSetup.assertsEqualsValue(config);
        subjectTestGetInfoRepo = new GetInfoRepo(service::getInfoRepo);
        subjectTestGetInfoRepos = new GetInfoRepos(service::getInfoRepos);
    }

    @Test
    public void testGetInfoRepoIsNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepo::subject, assertsNotEmpty);
    }

    @Test
    public void testGetInfoRepo() {
        unitTestExecutor.executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepo::subject, assertsEqualsValue);
    }

    @Test
    public void testGetInfoReposThenNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::subject, assertsNotEmpty);
    }

    @Test
    public void testGetInfoRepos() {
        unitTestExecutor.executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::subject, assertsEqualsValue);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetInfoReposForUserThenNotFound() {
        unitTestExecutor.executeTest("limjar-user-figury.properties", subjectTestGetInfoRepos::subject, assertsEqualsValue);
    }

    @Test
    public void testGetInfoReposForUserThenOk() {
        unitTestExecutor.executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::subject, assertsEqualsValue);
    }

}