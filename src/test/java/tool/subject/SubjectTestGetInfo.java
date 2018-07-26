package tool.subject;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

public interface SubjectTestGetInfo {
    InfoRepo execute(String owner, String repoName);
}
