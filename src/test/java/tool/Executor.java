package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import lombok.Builder;

import java.util.Optional;
import java.util.Properties;
import java.util.function.BiFunction;

@Builder
public class Executor {

    Properties dataTest;
    BiFunction<String, String, Optional<InfoRepo>> subjectTest;
    TestUnitConfig config;

    public Optional<InfoRepo> execute() {
        String owner = dataTest.getProperty("github.owner");
        String repoName = dataTest.getProperty("github.repoName");
        return subjectTest.apply(owner, repoName);
    }
}
