package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import lombok.Builder;

import java.util.Properties;
import java.util.function.BiFunction;

@Builder
class SubjectTestExecutor {

    Properties dataTest;
    BiFunction<String, String, InfoRepo> subjectTest;

    InfoRepo execute() {
        String owner = dataTest.getProperty("github.owner");
        String repoName = dataTest.getProperty("github.repoName");
        return subjectTest.apply(owner, repoName);
    }
}
