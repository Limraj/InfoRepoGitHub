package com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification
import spock.lang.Unroll

class InfoRepoPublicByOrganizationSpec extends Specification implements SampleInfoRepo {

    InfoRepoPublicByOrganizationFacade subject = new TestUnitConfiguration().infoRepoPublicByOrganizationFacade()

    @Unroll
    def "test getInfoRepo is OK"() {
        expect:
        subject.getInfoRepo(owner, repoName) == result

        where:
        owner    | repoName             | result
        "ruby"   | "ruby"               | rubyOrgRuby
        "ruby"   | "rubyspec.github.io" | rubyOrgRubySpec
        "ruby"   | "b.r-l.o"            | rubyUserBrLo
        "Limraj" | "figury"             | limrajUserFigury
        "Limraj" | "dicegame"           | limrajUserDicegame
    }

    @Unroll
    def "test getInfoRepo thrown HttpClientErrorException with HttpStatus.NOT_FOUND"() {
        when:
        subject.getInfoRepo(owner, repoName)

        then:
        def error = thrown(HttpClientErrorException)
        error.properties.get("statusCode") == statusCode

        where:
        owner     | repoName    | statusCode
        "Limraj"  | "dicegame2" | HttpStatus.NOT_FOUND
        "Limraj1" | "dicegame"  | HttpStatus.NOT_FOUND

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
        "ruby"   | rubyOrgRubySpec    | true
        "ruby"   | rubyOrgRuby        | true

    }

    @Unroll
    def "test getInfoRepos thrown HttpClientErrorException with HttpStatus.NOT_FOUND"() {

        when:
        subject.getInfoRepos(owner)

        then:
        def error = thrown(HttpClientErrorException)
        error.properties.get("statusCode") == statusCode

        where:
        owner    | statusCode
        "Limraj" | HttpStatus.NOT_FOUND
        "rubyaf" | HttpStatus.NOT_FOUND
    }
}
