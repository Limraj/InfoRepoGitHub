package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.util.PropertiesLoader;
import lombok.Builder;

import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Builder
public class UnitTestExecutor {

    private String pathToDataTest;

    public void executeTest(String fileName, BiFunction<String, String, Optional<InfoRepo>> subjectTest, BiConsumer<Optional<InfoRepo>, Properties> verifier) {
        //given:
        Properties dataTest = PropertiesLoader.loadProperties(pathToDataTest + fileName);
        System.out.println("dataTest: " + dataTest);
        //when:
        Optional<InfoRepo> infoRepo = Executor.builder().dataTest(dataTest).subjectTest(subjectTest).build().execute();
        System.out.println("infoRepo: " + infoRepo);
        //then:
        verifier.accept(infoRepo, dataTest);
    }

    public void executeTestRepoIsNotEmpty(String fileName, BiFunction<String, String, Optional<InfoRepo>> subjectTest, Consumer<Optional<InfoRepo>> verifier) {
        //given:
        Properties dataTest = PropertiesLoader.loadProperties(pathToDataTest + fileName);
        //when:
        Optional<InfoRepo> infoRepo = Executor.builder().dataTest(dataTest).subjectTest(subjectTest).build().execute();
        //then:
        verifier.accept(infoRepo);
    }

}


