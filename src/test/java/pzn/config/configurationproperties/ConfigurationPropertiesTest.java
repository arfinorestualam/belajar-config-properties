package pzn.config.configurationproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import pzn.config.converter.StringToDateConverter;
import pzn.config.properties.ApplicationProperties;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private ConversionService conversionService;

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

    @Test
    void testCollectionProperties() {
        Assertions.assertEquals(Arrays.asList("products", "customers","categories"), applicationProperties.getDatabase().getWhitelistTable());
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("products"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("customers"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTableSize().get("categories"));
    }

    @Test
    void testEmbeddedCollectionProperties() {
        Assertions.assertEquals("default", applicationProperties.getDefaultRoles().getFirst().getId());
        Assertions.assertEquals("Default Role", applicationProperties.getDefaultRoles().getFirst().getName());
        Assertions.assertEquals("guest", applicationProperties.getDefaultRoles().get(1).getId());
        Assertions.assertEquals("Guest Role", applicationProperties.getDefaultRoles().get(1).getName());

        Assertions.assertEquals("admin", applicationProperties.getRoles().get("admin").getId());
        Assertions.assertEquals("Admin Role", applicationProperties.getRoles().get("admin").getName());
        Assertions.assertEquals("finance", applicationProperties.getRoles().get("finance").getId());
        Assertions.assertEquals("Finance Role", applicationProperties.getRoles().get("finance").getName());
    }

    @Test
    void testDurationProperties() {
        //untuk mengecek apakah sudah ada conversion service yang menhandle convert dari yang kita inginkan
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertEquals(Duration.ofSeconds(10), applicationProperties.getTimeout());
    }

    @Test
    void testCustomConverter() {
        Date expireDate = applicationProperties.getExpiredDate();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //akan error karena belum di registrasi ke Conversion Service
        Assertions.assertEquals("2024-11-11", dateFormat.format(expireDate));
        //sesudah ditambahkan di TestApplication, sudah bisa di test.
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    //mendaftarkan ke conversion service secara manual
    @Import(StringToDateConverter.class)
    public static class TestApplication {

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }
}
