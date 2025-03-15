//package org.dows.log;
//
//import cn.hutool.extra.spring.EnableSpringUtil;
//import com.ververica.cdc.connectors.mysql.source.MySqlSource;
//import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
//import org.apache.flink.api.common.eventtime.WatermarkStrategy;
//import org.apache.flink.configuration.Configuration;
//import org.apache.flink.configuration.RestOptions;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.dows.log.handler.LogSinkHandler;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Properties;
//
//@SpringBootTest
//@EnableSpringUtil
//public class MysqlCDCTest {
//
//    @Autowired
//    private LogSinkHandler logSinkHandler;
//
//    private static MySqlSource<String> MY_SQL_SOURCE;
//
//    @Test
//    public void start() throws Exception {
//        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
//                .hostname("192.168.111.103")
//                .port(13306)
//                .username("root")
//                .password("shdy123!")
//                .databaseList("wes_all")
//                .tableList("wes_all.account_instance")
//                .deserializer(new JsonDebeziumDeserializationSchema())
//                .includeSchemaChanges(true)
//                .build();
//        System.out.println("init data source");
//
//        Configuration configuration = new Configuration();
//        configuration.setInteger(RestOptions.PORT, 8081);
//
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
//        env.enableCheckpointing(5000);
//        env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MYSQL Source").addSink(logSinkHandler);
//        env.execute();
//
//        System.out.println("env start");
//    }
//
//    //@Before
//    public void initMysqlSource() {
//        Properties debeziumProperties = new Properties();
//        // Flink SQL才需要添加debezium前缀，DataStreamAPI不需要加，不支持全匹配\\.*
//        // column.include.list不是监控到这几个字段有变化，而是监控所有字段，但只保留这几个字段
//        debeziumProperties.setProperty("column.include.list", "wes_all\\.account_instance\\.(account_instance_id|account_name),wes_all\\.user_instance\\.(user_instance_id|user_name|user_age)");
//        MY_SQL_SOURCE = MySqlSource.<String>builder()
//                .hostname("192.168.111.103")
//                .port(13306)
//                .username("root")
//                .password("shdy123!")
//                .databaseList("wes_all")
//                .tableList("wes_all.account_instance", "wes_all.user_instance")
//                .debeziumProperties(debeziumProperties)
//                .deserializer(new JsonDebeziumDeserializationSchema())
//                .build();
//        ;
//    }
//
//    /**
//     * 监听指定数据源并打印日志
//     * insert：表有主键，after会返回所有字段和字段的值
//     * update：表有主键，before和after都返回所有字段和字段的值
//     * delete：表有主键，before会返回所有字段和字段的值
//     * 注意这里跟postgresql不一样，mysql的返回值能更好的用到一些特殊的业务上
//     */
//    @Test
//    public void testPrintBinlog() throws Exception {
//        initMysqlSource();
//
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        // enable checkpoint
//        // 检查点间隔时间
//        env.enableCheckpointing(5000);
//        env.fromSource(MY_SQL_SOURCE, WatermarkStrategy.noWatermarks(), "MySQL CDC Source")
//                // set 4 parallel source tasks
//                // 任务数
//                .setParallelism(1)
//                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering
//        env.execute("Print MySQL Snapshot + Binlog");
//    }
//
//}
