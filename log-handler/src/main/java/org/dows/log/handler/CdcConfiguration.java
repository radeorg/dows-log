//package org.dows.log.handler;
//
//import jakarta.annotation.PostConstruct;
//import lombok.Data;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@Data
//@ConfigurationProperties("dows.cdc")
//public class CdcConfiguration {
//    @Getter
//    private List<CdcJobProperties> jobs;
//    @Autowired
//    private DatasourceContext datasourceContext;
//
//    private static Map<String, String> map = new HashMap<>();
//
//    @PostConstruct
//    public void init() {
//        //todo
//        for (CdcJobProperties job : jobs) {
//            map.put(job.getName(), job.getDatasourceName());
//        }
//    }
//
//
//    public DatasourceProperties getByDatasourceName(String datasourceName) {
//        DatasourceProperties datasourceProperties = datasourceContext.getDatasourceProperties(datasourceName);
//        if (datasourceProperties == null) {
//            throw new RuntimeException("不存在的数据源");
//        }
//        return datasourceProperties;
//    }
//
//    public String getDatasourceNameByJonName(String jobName) {
//        String s = map.get(jobName);
//        if (s == null) {
//            throw new RuntimeException("不存在的数据源");
//        }
//        return s;
//    }
////    public CdcSettings get(String key) {
////        for (CdcJobProperties job : jobs) {
////            job.getName()
////        }
////
////
////        for (CdcJobProperties job : jobs) {
////
////        }
////
////        return datasource.get(key);
////    }
//}