package com.gmail.kamil.jarmusik.DataRepoGithub.util;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PropertiesLoaderTest {

    @Test
    public void testLoadPropertiesThenLoadingCorrectData() {
        //when:
        Properties dataTest = PropertiesLoader.loadProperties("src/test/resources/loader/loader-test.properties");
        String owner =  dataTest.getProperty("github.owner");
        String repoName =  dataTest.getProperty("github.repoName");
        String fullName = dataTest.getProperty("github.repo.fullName");
        String description = dataTest.getProperty("github.repo.description");
        String cloneUrl = dataTest.getProperty("github.repo.cloneUrl");
        String stars = dataTest.getProperty("github.repo.stars");
        String createAt = dataTest.getProperty("github.repo.createAt");
        //then:
        assertEquals(owner, "ruby");
        assertEquals(repoName, "rubyspec.github.io");
        assertEquals(fullName, "ruby/rubyspec.github.io");
        assertEquals(description, "The website for RubySpec.");
        assertEquals(cloneUrl, "git://github.com/ruby/rubyspec.github.io.git");
        assertEquals(stars, "2");
        assertEquals(createAt, "2015-08-02");
    }
}