package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.Assert.*;

public class TestSetup {

    public static Consumer<InfoRepo> assertsNotEmpty() {
        return infoRepo -> {
            assertNotNull(infoRepo);
            assertTrue(isNotBlank(infoRepo.getCloneUrl()));
            assertTrue(isNotBlank(infoRepo.getFullName()));
            assertNotNull(infoRepo.getCreatedAt());
        };
    }

    public static BiConsumer<InfoRepo, Properties> assertsEqualsValue(TestUnitConfig config) {
        return new BiConsumer<InfoRepo, Properties>() {
            TestUnitConfig config;

            public BiConsumer<InfoRepo, Properties> setConfig(TestUnitConfig config) {
                this.config = config;
                return this;
            }

            @Override
            public void accept(InfoRepo infoRepo, Properties dataTest) {
                String fullName = dataTest.getProperty("github.repo.fullName");
                String createAt = dataTest.getProperty("github.repo.createAt");
                String cloneUrl = dataTest.getProperty("github.repo.cloneUrl");
                assertNotNull(infoRepo);
                assertEquals(fullName, infoRepo.getFullName());
                assertEquals(createAt, config.dateToString(infoRepo.getCreatedAt()));
                assertEquals(cloneUrl, infoRepo.getCloneUrl());
            }
        }.setConfig(config);
    }
}
