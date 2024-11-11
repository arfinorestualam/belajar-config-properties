package pzn.config.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

//annotation active profile, digunakan untuk menunjuk profile yang aktif langsung
//tanpa harus menulis di properties, dan bisa lebih dari 1
@SpringBootTest(classes = ProfileTest.TestApplication.class)
@ActiveProfiles({"production"})
public class ProfileTest {

    @Autowired
    private TestApplication.SayHello sayHello;

    @Test
    void testProfile() {
        Assertions.assertEquals("Hello fin from local",sayHello.say("fin"));
    }

    @SpringBootApplication
    public static class TestApplication {

        public interface SayHello {
            String say(String name);
        }

        //profile digunakan untuk di jalankan sesuai dengan yang ditulis di properties
        //kaya kita bikin koneksi ke db, ada yang local, dev, production
        //kita buat componentnya sesuai dengan profile yang ingin dituju
        //mirip env kalo di js
        //untuk profile jika tidak dibuat dan tidak di konfigurasi di properties
        //maka yang dijalankan otomatis adalah profile "default"
        @Component
        @Profile({
                "local"
        })
        public static class SayHelloLocal implements SayHello {

            @Override
            public String say(String name) {
                return "Hello " + name + " from local";
            }
        }

        @Component
        @Profile({
                "production"
        })
        public static class SayHelloProd implements SayHello {

            @Override
            public String say(String name) {
                return "Hello " + name + " from production";
            }
        }
    }
}
//Bisa juga menggunakan --spring.profiles.active=(langsung sebut dengan pisah koma, misal : production,test)
//Jalankan di terminal dengan cara buat dulu package sampai jadi file jar
//Lalu masukkan comman java -jar target/(nama jar) --spring.profiles.active=(langsung sebut dengan pisah koma, misal : production,test)
//ini cara mengubah active profile dari application properties
