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

public class InfoRepoPublicByOrganizationServiceTest {

    private static Consumer<InfoRepo> assertsByNotEmpty;
    private static BiConsumer<InfoRepo, Properties> assertsByEqualsToDataExpected;
    private static SubjectTestGetInfo subjectTestGetInfoRepos;
    private static SubjectTestGetInfo subjectTestGetInfoRepo;

    @BeforeClass
    public static void setup() {
        UnitTestExecutor.pathToDataExpected =  "src/test/resources/service/";
        TestUnitConfig config = new TestUnitConfig();
        assertsByNotEmpty = AssertsSetup.assertsByNotEmpty();
        assertsByEqualsToDataExpected = AssertsSetup.assertsByEqualsToDataExpected(config);

        InfoRepoService service = new InfoRepoPublicByOrganizationService(config.getRestTemplate());
        subjectTestGetInfoRepo = new GetInfoRepo(service::getInfoRepo);
        subjectTestGetInfoRepos = new GetInfoRepos(service::getInfoRepos);
    }

    @Test
    public void testGetInfoRepoThenNotEmpty() {
        executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepo::execute, assertsByNotEmpty);
    }

    @Test
    public void testGetInfoRepoThenEquals() {
        executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepo::execute, assertsByEqualsToDataExpected);
    }

    @Test
    public void testGetInfoReposThenNotEmpty() {
        executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::execute, assertsByNotEmpty);
    }

    @Test
    public void testGetInfoReposThenEquals() {
        executeTest("ruby-organization-rubyspec.github.io.properties", subjectTestGetInfoRepos::execute, assertsByEqualsToDataExpected);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetInfoReposForUserThenNotFound() {
        executeTest("limjar-user-figury.properties", subjectTestGetInfoRepos::execute, assertsByEqualsToDataExpected);
    }

    @Test
    public void testGetInfoReposForUserThenOk() {
        executeTest("ruby-user-b.r-l.o.properties", subjectTestGetInfoRepos::execute, assertsByEqualsToDataExpected);
    }

}