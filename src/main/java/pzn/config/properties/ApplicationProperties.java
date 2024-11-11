package pzn.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {

    private String name;
    private Integer version;
    private boolean productionMode;
    //masuk ke complex configuration properties
    private DatabaseProperties database;
    //masuk embed collection
    private List<Role> defaultRoles;
    private Map<String, Role> roles;

    @Getter
    @Setter
    public static class DatabaseProperties {
        private String url;
        private String username;
        private String password;
        private String database;
        //masuk ke collection configuration properties
        private List<String> whitelistTable;
        private Map<String, Integer> maxTableSize;
    }

    //masuk embed collection
    @Getter
    @Setter
    public static class Role{
        private String id;
        private String name;
    }
}
