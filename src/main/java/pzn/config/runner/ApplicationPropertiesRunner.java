package pzn.config.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pzn.config.properties.ApplicationProperties;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationPropertiesRunner implements ApplicationRunner {

    private ApplicationProperties applicationProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(applicationProperties.getName());
        log.info(String.valueOf(applicationProperties.getVersion()));
        log.info(String.valueOf(applicationProperties.isProductionMode()));
    }
}
