package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain

import groovy.transform.CompileStatic
import tool.PropertiesLoader

import java.text.DateFormat

@CompileStatic
trait SampleInfoRepo {

    static TestUnitConfiguration testUnitConfiguration = new TestUnitConfiguration()

    InfoRepo limrajUserFigury = createInfoRepo("limraj-user-figury.properties")
    InfoRepo limrajUserDicegame = createInfoRepo("limraj-user-dicegame.properties")
    InfoRepo rubyOrgRuby = createInfoRepo("ruby-organization-ruby.properties")
    InfoRepo rubyOrgRubySpec = createInfoRepo("ruby-organization-rubyspec.github.io.properties")
    InfoRepo rubyUserBrLo = createInfoRepo("ruby-user-b.r-l.o.properties")

    static private InfoRepo createInfoRepo(String fileName) {
        String pathToDataExpected =  "src/test/resources/service/"
        Properties properties = PropertiesLoader.load(pathToDataExpected + fileName)
        DateFormat format = testUnitConfiguration.getDateFormat()
        Date createAt = format.parse(properties.getProperty("github.repo.createAt"))
        int stars = Integer.valueOf(properties.getProperty("github.repo.stars"))
        return InfoRepo.builder()
            .fullName(properties.getProperty("github.repo.fullName"))
            .description(properties.getProperty("github.repo.description"))
            .cloneUrl(properties.getProperty("github.repo.cloneUrl"))
            .stars(stars)
            .createdAt(createAt)
            .build()
    }

}
