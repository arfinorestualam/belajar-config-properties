package pzn.config.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Test
    void testEnvironment() {
        String javaHome = environment.getProperty("JAVA_HOME");
        System.out.println(javaHome);
        //error kalo di assertion, soalnya ngga ada java_home.
        //Assertions.assertEquals("ini harusnya letak java home", javaHome);
    }

    @SpringBootApplication
    public static class TestApplication {


    }
}
