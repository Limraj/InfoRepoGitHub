package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.jarmusik.kamil.DataRepoGithub.info.domain.InfoRepo
import com.gmail.jarmusik.kamil.DataRepoGithub.info.domain.TestUnitConfiguration
import groovy.transform.CompileStatic
import tool.JsonDeserializer

@CompileStatic
trait SampleInfoRepo {

    InfoRepo limrajUserFigury = createInfoRepoFromFile("limraj-user-figury.json")
    InfoRepo limrajUserDicegame = createInfoRepoFromFile("limraj-user-dicegame.json")
    InfoRepo rubyOrgRuby = createInfoRepoFromFile("ruby-org-ruby.json")
    InfoRepo rubyOrgRubySpec = createInfoRepoFromFile("ruby-org-rubyspec.github.io.json")
    InfoRepo rubyUserBrLo = createInfoRepoFromFile("ruby-user-b.r-l.o.json")

    static InfoRepo createInfoRepoFromFile(String fileName) {
        String jsonString = new File("src/test/resources/repo/" + fileName).text
        ObjectMapper objectMapperDeserialize = TestUnitConfiguration.getInstance().objectMapperDeserialize()
        return JsonDeserializer.deserialize(objectMapperDeserialize, jsonString)
    }

}
