package tool;

import com.gmail.kamil.jarmusik.DataRepoGithub.config.TestUnitConfig;
import com.gmail.kamil.jarmusik.DataRepoGithub.resource.InfoRepo;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.Assert.*;

public class AssertsSetup {

    public static Consumer<InfoRepo> assertsByNotEmpty() {
        return infoRepo -> {
            assertNotNull(infoRepo);
            assertTrue(isNotBlank(infoRepo.getCloneUrl()));
            assertTrue(isNotBlank(infoRepo.getFullName()));
            assertNotNull(infoRepo.getCreatedAt());
        };
    }

    public static BiConsumer<InfoRepo, Properties> assertsByEqualsToDataExpected(TestUnitConfig config) {
        return new BiConsumer<InfoRepo, Properties>() {
            TestUnitConfig config;

            public BiConsumer<InfoRepo, Properties> setConfig(TestUnitConfig config) {
                this.config = config;
                return this;
            }

            @Override
            public void accept(InfoRepo infoRepo, Properties dataExpected) {
                String fullName = dataExpected.getProperty("github.repo.fullName");
                String createAt = dataExpected.getProperty("github.repo.createAt");
                String cloneUrl = dataExpected.getProperty("github.repo.cloneUrl");
                assertNotNull(infoRepo);
                assertEquals(fullName, infoRepo.getFullName());
                assertEquals(createAt, config.dateToString(infoRepo.getCreatedAt()));
                assertEquals(cloneUrl, infoRepo.getCloneUrl());
            }
        }.setConfig(config);
    }
}
