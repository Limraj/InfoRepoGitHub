package com.gmail.kamil.jarmusik.DataRepoGithub.util;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InfoRepoSelectorTest {

    private static List<InfoRepo> repos;
    private static TestUnitConfig config;

    @BeforeClass
    public static void setup() {
        repos = new ArrayList<>();

        InfoRepo infoRepo = new InfoRepo();
        infoRepo.setCloneUrl("cloneUrl1");
        infoRepo.setCreatedAt(new Date());
        infoRepo.setDescription("anything1");
        infoRepo.setFullName("Limraj/figury");
        infoRepo.setStars(11);

        InfoRepo infoRepo2 = new InfoRepo();
        infoRepo2.setCloneUrl("cloneUrl2");
        infoRepo2.setCreatedAt(new Date());
        infoRepo2.setDescription("anything2");
        infoRepo2.setFullName("ruby/b.r-l.o");
        infoRepo2.setStars(22);

        InfoRepo infoRepo3 = new InfoRepo();
        infoRepo3.setCloneUrl("cloneUrl3");
        infoRepo3.setCreatedAt(new Date());
        infoRepo3.setDescription("anything3");
        infoRepo3.setFullName("ruby/rubyspec.github.io");
        infoRepo3.setStars(33);

        repos.add(infoRepo);
        repos.add(infoRepo2);
        repos.add(infoRepo3);

        config = new TestUnitConfig();
    }

    @Test
    public void selectInfoRepoByFullName1() {
        //given:
        String owner = "ruby";
        String repoName = "b.r-l.o";
        //when:
        InfoRepo infoRepo = InfoRepoSelector.selectInfoRepo(owner, repoName, repos);
        //then:
        assertNotNull(infoRepo);
        assertEquals("ruby/b.r-l.o", infoRepo.getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.getCreatedAt()));
        assertEquals("cloneUrl2", infoRepo.getCloneUrl());
        assertEquals("anything2", infoRepo.getDescription());
        assertEquals(22, infoRepo.getStars());
    }

    @Test
    public void selectInfoRepoByFullName2() {
        //given:
        String owner = "Limraj";
        String repoName = "figury";
        //when:
        InfoRepo infoRepo = InfoRepoSelector.selectInfoRepo(owner, repoName, repos);
        //then:
        assertNotNull(infoRepo);
        assertEquals("Limraj/figury", infoRepo.getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.getCreatedAt()));
        assertEquals("cloneUrl1", infoRepo.getCloneUrl());
        assertEquals("anything1", infoRepo.getDescription());
        assertEquals(11, infoRepo.getStars());
    }

    @Test
    public void selectInfoRepoByFullName3() {
        //given:
        String owner = "ruby";
        String repoName = "rubyspec.github.io";
        //when:
        InfoRepo infoRepo = InfoRepoSelector.selectInfoRepo(owner, repoName, repos);
        //then:
        assertNotNull(infoRepo);
        assertEquals("ruby/rubyspec.github.io", infoRepo.getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.getCreatedAt()));
        assertEquals("cloneUrl3", infoRepo.getCloneUrl());
        assertEquals("anything3", infoRepo.getDescription());
        assertEquals(33, infoRepo.getStars());
    }

    @Test
    public void createFullName() {
        //given:
        String owner = "ruby";
        String repoName = "rubyspec.github.io";
        //when:
        String fullName = InfoRepoSelector.createFullName(owner, repoName);
        assertEquals("ruby/rubyspec.github.io", fullName);
    }
}