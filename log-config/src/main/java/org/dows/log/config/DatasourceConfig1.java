//package org.dows.log.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.annotation.PostConstruct;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * @description: </br>
// * @author: lait.zhang@gmail.com
// * @date: 7/2/2024 2:26 PM
// * @history: </br>
// * <author>      <time>      <version>    <desc>
// * 修改人姓名      修改时间        版本号       描述
// */
//@Slf4j
//@Data
//@Component
//@ConfigurationProperties(prefix = "dows.config")
//public class DatasourceConfig {
//
//    private Map<String, DataSourceProperties> datasource;
//
//    private final static Map<String, DataSource> dataSourceMap = new HashMap<>();
//    private final static Map<String, DataSourceProperties> dataSourcePropertiesMap = new HashMap<>();
//    @Autowired
//    private Environment env;
//
//
//    public DataSource getDataSource(String key) {
//        DataSource dataSource = dataSourceMap.get(key);
//        if (dataSource == null) {
//            throw new RuntimeException("datasource not found");
//        }
//        return dataSource;
//    }
//
//
//    public DataSourceProperties getdataSourceProperties(String key) {
//        DataSourceProperties dsp = dataSourcePropertiesMap.get(key);
//        if (dsp == null) {
//            throw new RuntimeException("datasource properties not found");
//        }
//        return dsp;
//    }
//
//    @PostConstruct
//    public void init() {
//        datasource.forEach((k, dataSourceProperties) -> {
//            HikariConfig configuration = env.getProperty("dows.settings.datasource." + k + ".hikari", HikariConfig.class);
//            if (configuration != null) {
//                HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder.create()
//                        .type(dataSourceProperties.getType())
//                        .driverClassName(dataSourceProperties.getDriverClassName())
//                        .url(dataSourceProperties.getUrl())
//                        .username(dataSourceProperties.getUsername())
//                        .password(dataSourceProperties.getPassword())
//                        .build();
//                configuration.copyStateTo(hikariDataSource);
//                dataSourceMap.put(k, hikariDataSource);
//                dataSourcePropertiesMap.put(k, dataSourceProperties);
//            } else {
//                log.error("datasource config error, please check your config");
//            }
//        });
//    }
//
//}
