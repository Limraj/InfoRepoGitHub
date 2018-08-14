package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification
import spock.lang.Unroll

class InfoRepoPublicFacadeSpec extends Specification implements SampleInfoRepo  {
    def subject = TestUnitConfiguration.getInstance().infoRepoPublicFacade()

    @Unroll
    def "test getInfoRepo is OK"() {
        expect:
        subject.getInfoRepo(owner, repoName) == result

        where:
        owner    | repoName             | result
        "ruby"   | "b.r-l.o"            | rubyUserBrLo
        "Limraj" | "figury"             | limrajUserFigury
        "Limraj" | "dicegame"           | limrajUserDicegame
        "ruby"   | "ruby"               | rubyOrgRuby
        "ruby"   | "rubyspec.github.io" | rubyOrgRubySpec
    }


    @Unroll
    def "test getInfoRepo thrown HttpClientErrorException with HttpStatus.NOT_FOUND"() {
        when:
        subject.getInfoRepo(owner, repoName)

        then:
        def error = thrown(HttpClientErrorException)
        error.properties.get("statusCode") == statusCode

        where:
        owner        | repoName          | statusCode
        "Limraj"     | "dicegamsdfgagwe" | HttpStatus.NOT_FOUND
        "Limrasdfj1" | "dicegame"        | HttpStatus.NOT_FOUND

    }

}
