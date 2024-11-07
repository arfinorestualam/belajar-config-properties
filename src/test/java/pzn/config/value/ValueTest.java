package pzn.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    TestApplication.ApplicationProperties applicationProperties;

    @Autowired
    TestApplication.SystemProperties systemProperties;

    @Test
    void testApplicationProperties() {
        Assertions.assertEquals("config-properties", applicationProperties.getApplicationName());
        Assertions.assertEquals(1,applicationProperties.getApplicationVersion());
        Assertions.assertFalse(applicationProperties.getApplicationProductionMode());
    }

    @Test
    void testSystemProperties() {
        Assertions.assertEquals("isi dengan letak java_home",systemProperties.getJavaHome());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class ApplicationProperties {
            //penggunaan annotation value untuk mengambil data di application.properties
            //atau di system properties
            @Value("${spring.application.name}")
            private String applicationName;

            @Value("${application.version}")
            private Integer applicationVersion;

            @Value("${application.production-mode}")
            private Boolean applicationProductionMode;

        }

        @Component
        @Getter
        public static class SystemProperties {

            @Value("${JAVA_HOME}")
            private String javaHome;
        }
    }
}
