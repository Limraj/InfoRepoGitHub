package com.gmail.kamil.jarmusik.DataRepoGithub.api;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import tool.AssertsSetup;
import tool.UnitTestExecutor;
import tool.subject.GetInfoRepo;
import tool.subject.GetInfoRepos;
import tool.subject.SubjectTestGetInfo;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static tool.UnitTestExecutor.executeTest;

public class InfoRepoPublicByUserServiceTest {

    private static Consumer<InfoRepo> assertsByNotEmpty;
    private static BiConsumer<InfoRepo, Properties> assertsByEqualsToDataExpected;
    private static SubjectTestGetInfo subjectTestGetInfoRepos;
    private static SubjectTestGetInfo subjectTestGetInfoRepo;

    @BeforeClass
    public static void setup() {
        UnitTestExecutor.pathToDataExpected =  "src/test/resources/service/";
        TestUnitConfig  config = new TestUnitConfig();
        assertsByNotEmpty = AssertsSetup.assertsByNotEmpty();
        assertsByEqualsToDataExpected = AssertsSetup.assertsByEqualsToDataExpected(config);

        InfoRepoService service = new InfoRepoPublicByUserService(config.getRestTemplate());
        subjectTestGetInfoRepo = new GetInfoRepo(service::getInfoRepo);
        subjectTestGetInfoRepos = new GetInfoRepos(service::getInfoRepos);
    }

    @Test
    public void testGetInfoRepoThenNotEmpty() {
        executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepo::execute, assertsByNotEmpty);
    }

    @Test
    public void testGetInfoRepoThenEqualsToDataExpected() {
        executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepo::execute, assertsByEqualsToDataExpected);
    }

    @Test
    public void testGetInfoReposThenNotEmpty() {
        executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::execute, assertsByNotEmpty);
    }

    @Test
    public void testGetInfoReposThenEqualsToDataExpected() {
        executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::execute, assertsByEqualsToDataExpected);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetInfoReposForOrganizationThenNotFound() {
        executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::execute, assertsByEqualsToDataExpected);
    }

}