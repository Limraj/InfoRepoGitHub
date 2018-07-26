package tool.subject;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.function.BiFunction;

public class GetInfoRepo implements SubjectTestGetInfo {

    private BiFunction<String, String, InfoRepo> subjectTest;

    public GetInfoRepo(BiFunction<String, String, InfoRepo> subjectTest) {
        this.subjectTest = subjectTest;
    }

    @Override
    public InfoRepo execute(String owner, String repoName) {
        return subjectTest.apply(owner, repoName);
    }
}
