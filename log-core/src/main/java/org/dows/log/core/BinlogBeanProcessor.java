package org.dows.log.core;

import org.dows.log.api.BinlogListener;
import org.dows.log.config.LogConfig;
import org.dows.log.core.thread.BinlogThreadStarter;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//@Component
public class BinlogBeanProcessor implements SmartInitializingSingleton, EnvironmentAware {
    private ApplicationContext context;
    private Environment environment;
    @Autowired
    private LogConfig logConfig;

    public BinlogBeanProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, BinlogListener> beans = context.getBeansOfType(BinlogListener.class);
        Map<String, List<MysqlListener>> listeners = beans.values().stream()
                .map(l -> new MysqlListener(l, environment))
                .collect(Collectors.groupingBy(MysqlListener::getHostName));
        listeners.forEach((k, v) -> new BinlogThreadStarter().runThread(logConfig.getByNameAndThrow(k), v));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
