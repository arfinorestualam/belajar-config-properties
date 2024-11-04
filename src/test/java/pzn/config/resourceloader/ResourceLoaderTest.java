package pzn.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

//agar tidak terscan otomatis, dipindahkan ke folder lain
@SpringBootTest(classes = ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {

    @Autowired
    private TestApplication.SampleResource sampleResource;

    @Test
    void testResouceLoader() throws IOException {
        Assertions.assertEquals("Test mencari resource.", sampleResource.getText().trim());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Setter
        @Component
        public static class SampleResource implements ResourceLoaderAware {

            private ResourceLoader resourceLoader;

            public String getText() throws IOException {
                //getResoucer() tergantung prefix atau kata di depannya setelah " jika classpath yang dicari classpath
                //jika http, yang dicari url. jika file, yang dicari file.
                Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
                try (var inputStream = resource.getInputStream()) {
                    return new String(inputStream.readAllBytes());
                }
            }
        }
    }
}
