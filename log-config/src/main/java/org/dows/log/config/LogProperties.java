package org.dows.log.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "dows.log")
public class LogProperties {
    // disable or enable
    private String cdc;
    private boolean actLogScan;

    private Map<String,AppLogCollectionName> appLogCollectionNames;


//    private String name;
//    private String host;
//    private int port;
//    private String username;
//    private String password;
//    private long timeOffset;
//    private List<String> entityPackages;
//    @NestedConfigurationProperty
//    private TableFilter filter;


    @Data
    public static class AppLogCollectionName {
        // nosql[mongo,es] collection name
        private String actlog;
        // nosql[mongo,es] collection name
        private String binlog;
    }
}
