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

public class GitHubRepoPublicByUserServiceTest {

    private static UnitTestExecutor unitTestExecutor;
    private static Consumer<InfoRepo> assertsNotEmpty;
    private static BiConsumer<InfoRepo, Properties> assertsEqualsValue;
    private static SubjectTestGetInfo subjectTestGetInfoRepos;
    private static SubjectTestGetInfo subjectTestGetInfoRepo;

    @BeforeClass
    public static void setup() {
        TestUnitConfig  config = new TestUnitConfig();
        GitHubRepoService service = new GitHubRepoPublicByUserService(config.getRestTemplate());
        unitTestExecutor =  new UnitTestExecutor("src/test/resources/service/");
        assertsNotEmpty = TestSetup.assertsNotEmpty();
        assertsEqualsValue = TestSetup.assertsEqualsValue(config);
        subjectTestGetInfoRepo = new GetInfoRepo(service::getInfoRepo);
        subjectTestGetInfoRepos = new GetInfoRepos(service::getInfoRepos);
    }

    @Test
    public void testGetInfoRepoThenNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepo::execute, assertsNotEmpty);
    }

    @Test
    public void testGetInfoRepoThenEquals() {
        unitTestExecutor.executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepo::execute, assertsEqualsValue);
    }

    @Test
    public void testGetInfoReposThenNotEmpty() {
        unitTestExecutor.executeTestRepoIsNotEmpty("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::execute, assertsNotEmpty);
    }

    @Test
    public void testGetInfoReposThenEquals() {
        unitTestExecutor.executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::execute, assertsEqualsValue);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetInfoReposForOrganizationThenNotFound() {
        unitTestExecutor.executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::execute, assertsEqualsValue);
    }

}