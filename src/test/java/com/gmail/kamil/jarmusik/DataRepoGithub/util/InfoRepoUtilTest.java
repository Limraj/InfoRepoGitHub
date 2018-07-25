package com.gmail.kamil.jarmusik.DataRepoGithub.util;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InfoRepoUtilTest {

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
        Optional<InfoRepo> infoRepo = InfoRepoUtil.selectInfoRepoByFullName(InfoRepoUtil.createFullName(owner,repoName), repos);
        //then:
        assertTrue(infoRepo.isPresent());
        assertEquals("ruby/b.r-l.o", infoRepo.get().getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.get().getCreatedAt()));
        assertEquals("cloneUrl2", infoRepo.get().getCloneUrl());
        assertEquals("anything2", infoRepo.get().getDescription());
        assertEquals(22, infoRepo.get().getStars());
    }

    @Test
    public void selectInfoRepoByFullName2() {
        //given:
        String owner = "Limraj";
        String repoName = "figury";
        //when:
        Optional<InfoRepo> infoRepo = InfoRepoUtil.selectInfoRepoByFullName(InfoRepoUtil.createFullName(owner,repoName), repos);
        //then:
        assertTrue(infoRepo.isPresent());
        assertEquals("Limraj/figury", infoRepo.get().getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.get().getCreatedAt()));
        assertEquals("cloneUrl1", infoRepo.get().getCloneUrl());
        assertEquals("anything1", infoRepo.get().getDescription());
        assertEquals(11, infoRepo.get().getStars());
    }

    @Test
    public void selectInfoRepoByFullName3() {
        //given:
        String owner = "ruby";
        String repoName = "rubyspec.github.io";
        //when:
        Optional<InfoRepo> infoRepo = InfoRepoUtil.selectInfoRepoByFullName(InfoRepoUtil.createFullName(owner,repoName), repos);
        //then:
        assertTrue(infoRepo.isPresent());
        assertEquals("ruby/rubyspec.github.io", infoRepo.get().getFullName());
        assertEquals( config.dateToString(new Date()), config.dateToString(infoRepo.get().getCreatedAt()));
        assertEquals("cloneUrl3", infoRepo.get().getCloneUrl());
        assertEquals("anything3", infoRepo.get().getDescription());
        assertEquals(33, infoRepo.get().getStars());
    }

    @Test
    public void createFullName() {
        //given:
        String owner = "ruby";
        String repoName = "rubyspec.github.io";
        //when:
        String fullName = InfoRepoUtil.createFullName(owner, repoName);
        assertEquals("ruby/rubyspec.github.io", fullName);
    }
}