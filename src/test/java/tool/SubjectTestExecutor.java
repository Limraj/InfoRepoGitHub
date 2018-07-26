package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import lombok.Builder;

import java.util.Properties;
import java.util.function.BiFunction;

@Builder
public class SubjectTestExecutor {

    Properties dataTest;
    BiFunction<String, String, InfoRepo> subjectTest;

    public InfoRepo execute() {
        String owner = dataTest.getProperty("github.owner");
        String repoName = dataTest.getProperty("github.repoName");
        return subjectTest.apply(owner, repoName);
    }
}
