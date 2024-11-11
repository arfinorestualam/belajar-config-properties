package pzn.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import pzn.config.converter.StringToDateConverter;
import pzn.config.properties.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        ApplicationProperties.class
})
public class ConfigProperitesApplication {

    @Bean
    public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
        ApplicationConversionService conversionService = new ApplicationConversionService();
        conversionService.addConverter(stringToDateConverter);
        return conversionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigProperitesApplication.class, args);
    }

}
