package tool.subject;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

public interface SubjectTestGetInfo {
    InfoRepo subject(String owner, String repoName);
}
