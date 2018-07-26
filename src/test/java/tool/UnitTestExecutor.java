package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;
import com.gmail.kamil.jarmusik.DataRepoGithub.util.PropertiesLoader;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class UnitTestExecutor {

    public static String pathToDataExpected = "";

    public static void executeTest(String fileName, BiFunction<String, String, InfoRepo> subjectTest, BiConsumer<InfoRepo, Properties> verifier) {
        //given:
        Properties dataTest = PropertiesLoader.loadProperties(pathToDataExpected + fileName);
        //when:
        InfoRepo infoRepo = SubjectTestExecutor.builder().dataTest(dataTest).subjectTest(subjectTest).build().execute();
        //then:
        verifier.accept(infoRepo, dataTest);
    }

    public static void executeTest(String fileName, BiFunction<String, String, InfoRepo> subjectTest, Consumer<InfoRepo> verifier) {
        //given:
        Properties dataTest = PropertiesLoader.loadProperties(pathToDataExpected + fileName);
        //when:
        InfoRepo infoRepo = SubjectTestExecutor.builder().dataTest(dataTest).subjectTest(subjectTest).build().execute();
        //then:
        verifier.accept(infoRepo);
    }

}


