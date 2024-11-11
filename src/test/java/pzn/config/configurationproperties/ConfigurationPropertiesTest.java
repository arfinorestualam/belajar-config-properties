package pzn.config.configurationproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import pzn.config.properties.ApplicationProperties;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("config-properties",applicationProperties.getName());
        Assertions.assertEquals(1,applicationProperties.getVersion());
        Assertions.assertEquals(false,applicationProperties.isProductionMode());
    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("fin",applicationProperties.getDatabase().getUsername());
        Assertions.assertEquals("secret",applicationProperties.getDatabase().getPassword());
        Assertions.assertEquals("learn", applicationProperties.getDatabase().getDatabase());
        Assertions.assertEquals("jdbc:contoh",applicationProperties.getDatabase().getUrl());
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    public static class TestApplication {


    }
}
