package org.dows.log.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class LogConfig {
    private final LogProperties logProperties;

    private final ApplicationContext applicationContext;

    public String getActlogCollectionName() {

        String applicationName = this.applicationContext.getEnvironment().getProperty("spring.application.name");
        LogProperties.AppLogCollectionName appLogCollectionName = logProperties.getAppLogCollectionNames().get(applicationName);
        if (appLogCollectionName == null) {
            throw new RuntimeException("未配置名为 " + applicationName + " 的日志收集名称");
        }
        return appLogCollectionName.getActlog();
    }

    public String getBinlogCollectionName() {
        String applicationName = this.applicationContext.getEnvironment().getProperty("spring.application.name");
        LogProperties.AppLogCollectionName appLogCollectionName = logProperties.getAppLogCollectionNames().get(applicationName);
        if (appLogCollectionName == null) {
            throw new RuntimeException("未配置名为 " + applicationName + " 的日志收集名称");
        }
        return appLogCollectionName.getBinlog();
    }

    public String getAppName() {
        return this.applicationContext.getEnvironment().getProperty("spring.application.name");
    }

//    private List<LogProperties> hosts;
//    public List<LogProperties> getHosts() {
//        return hosts;
//    }
//    public void setHosts(List<LogProperties> hosts) {
//        this.hosts = hosts;
//    }
//    public Optional<LogProperties> getByName(String name) {
//        return hosts.stream().filter(v -> name.equals(v.getHost()))
//                .findAny();
//    }
//
//    public LogProperties getByNameAndThrow(String name) {
//        return getByName(name).orElseThrow(() -> new RuntimeException("未配置名为 " + name + " 的 binlog 连接信息"));
//    }
}
