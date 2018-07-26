package tool.subject;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.util.InfoRepoSelector;

import java.util.List;
import java.util.function.Function;

public class GetInfoRepos implements SubjectTestGetInfo {

    Function<String, List<InfoRepo>> subjectTest;

    public GetInfoRepos(Function<String, List<InfoRepo>> subjectTest) {
        this.subjectTest = subjectTest;
    }

    @Override
    public InfoRepo execute(String owner, String repoName) {
        List<InfoRepo> infoRepos = subjectTest.apply(owner);
        return InfoRepoSelector.selectInfoRepo(owner, repoName, infoRepos);
    }
}
