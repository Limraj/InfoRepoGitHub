package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification
import spock.lang.Unroll

class InfoRepoPublicByUserSpec extends Specification implements SampleInfoRepo {

    InfoRepoPublicByUserFacade subject = new TestUnitConfiguration().infoRepoPublicByUserFacade()

    @Unroll
    def "test getInfoRepo is OK"() {
        expect:
        subject.getInfoRepo(owner, repoName) == result

        where:
        owner     | repoName             | result
        "ruby"    | "b.r-l.o"            | rubyUserBrLo
        "Limraj"  | "figury"             | limrajUserFigury
        "Limraj"  | "dicegame"           | limrajUserDicegame
    }

    @Unroll
    def "test getInfoRepo thrown HttpClientErrorException with HttpStatus.NOT_FOUND"() {
        when:
        subject.getInfoRepo(owner, repoName)

        then:
        def error = thrown(HttpClientErrorException)
        error.properties.get("statusCode") == statusCode

        where:
        owner    | repoName             | statusCode
        "Limraj" | "dicegame2"          | HttpStatus.NOT_FOUND
        "Limraj1" | "dicegame"          | HttpStatus.NOT_FOUND

    }

    @Unroll
    def "test getInfoRepos is OK"() {

        when:
        List<InfoRepo> repos = subject.getInfoRepos(owner)

        then:
        repos.contains(repo) == result

        where:
        owner    | repo               | result
        "ruby"   | rubyUserBrLo       | true
        "ruby"   | rubyOrgRubySpec    | false
        "ruby"   | rubyOrgRuby        | false
        "Limraj" | limrajUserFigury   | true
        "Limraj" | limrajUserDicegame | true
    }
}
