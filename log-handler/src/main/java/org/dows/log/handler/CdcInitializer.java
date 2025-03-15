//package org.dows.log.handler;
//
//import cn.hutool.json.JSONUtil;
//import com.ververica.cdc.connectors.base.options.StartupOptions;
//import com.ververica.cdc.connectors.sqlserver.SqlServerSource;
//import com.ververica.cdc.debezium.DebeziumSourceFunction;
//import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.flink.api.common.functions.Function;
//import org.apache.flink.streaming.api.CheckpointingMode;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.functions.sink.SinkFunction;
//import org.dows.task.config.DatasourceProperties;
//
//import java.util.List;
//import java.util.Properties;
//
///**
// * @description: SQL server CDC变更监听器
// * @author: lait.zhang@gmail.com
// * @date: 7/2/2024 5:20 PM
// * @history: </br>
// * <author>      <time>      <version>    <desc>
// * 修改人姓名      修改时间        版本号       描述
// */
////@RequiredArgsConstructor
////@Configuration
////@EnableConfigurationProperties(CdcConfiguration.class)
//@Slf4j
//public class CdcInitializer /*implements ApplicationRunner, Serializable */ {
//
//    public static void run(CdcConfiguration cdcConfiguration) throws Exception {
//
//        List<CdcJobProperties> jobs = cdcConfiguration.getJobs();
//        for (CdcJobProperties job : jobs) {
//            DatasourceProperties datasourceProperties = cdcConfiguration.getByDatasourceName(job.getDatasourceName());
//            String driverClassName = datasourceProperties.getDriverClassName();
//            if (driverClassName.contains("sqlserver")) {
//                buildMssqlJob(job, datasourceProperties);
//            } else if (driverClassName.contains("maria")) {
//                buildMysqlJob(job, datasourceProperties);
//            }
//        }
//    }
//
//
//    private static void buildMssqlJob(CdcJobProperties job, DatasourceProperties datasourceProperties) {
//        log.info("开始启动Flink CDC获取{}变更数据......", JSONUtil.toJsonStr(job));
//        DebeziumSourceFunction<String> dataChangeInfoMySqlSource = buildDataChangeSource(job, datasourceProperties);
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        // 设置全局并行度
//        env.setParallelism(1);
//        // 设置时间语义为ProcessingTime
//        env.getConfig().setAutoWatermarkInterval(0);
//        // 每隔60s启动一个检查点
//        env.enableCheckpointing(60000, CheckpointingMode.EXACTLY_ONCE);
//        // checkpoint最小间隔
//        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(1000);
//        // checkpoint超时时间
//        env.getCheckpointConfig().setCheckpointTimeout(60000);
//        // 同一时间只允许一个checkpoint
//        // env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
//        // Flink处理程序被cancel后，会保留Checkpoint数据
//        //   env.getCheckpointConfig().setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//        Class<? extends Function> sinkClass = job.getSinkClass();
//        if (sinkClass != null) {
//            try {
//                Function function = sinkClass.getDeclaredConstructor().newInstance();
//                env.addSource(dataChangeInfoMySqlSource, job.getDatasourceName()).addSink((SinkFunction<String>) function);
//                // web console
//        /*org.apache.flink.configuration.Configuration configuration = new org.apache.flink.configuration.Configuration();
//        configuration.set(RestOptions.PORT, 8081);*/
//                env.execute(job.getName());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//
//    /**
//     * 构造CDC数据源
//     */
//    private static DebeziumSourceFunction<String> buildDataChangeSource(CdcJobProperties job, DatasourceProperties datasourceProperties) {
//
//        List<String> tableList = datasourceProperties.getTables();
//        String[] tables = new String[tableList.size()];
//        tableList.toArray(tables);
//
//        Properties properties = job.getProperties();
//        properties.setProperty("characterEncoding", "UTF-8");
//        properties.setProperty("characterSetResult", "UTF-8");
//        //encrypt=true;trustServerCertificate=true
//        properties.setProperty("encrypt", "true");
//        properties.setProperty("trustServerCertificate", "true");
//        // 自定义格式，可选
//        properties.put("sqlserverDebeziumConverter.format.datetime", "yyyy-MM-dd HH:mm:ss");
//        properties.put("sqlserverDebeziumConverter.format.date", "yyyy-MM-dd");
//        properties.put("sqlserverDebeziumConverter.format.time", "HH:mm:ss");
//
//        return SqlServerSource.<String>builder()
//                .hostname(datasourceProperties.getHost())
//                .port(datasourceProperties.getPort())
//                .database(datasourceProperties.getDatabase())
//                .tableList(tables)
//                .username(datasourceProperties.getUsername())
//                .password(datasourceProperties.getPassword())
//                .debeziumProperties(properties)
//                /*
//                 *initial初始化快照,即全量导入后增量导入(检测更新数据写入)
//                 * latest:只进行增量导入(不读取历史变化)
//                 */
//                .startupOptions(StartupOptions.latest())
//                .deserializer(new JsonDebeziumDeserializationSchema())
//                .build();
//    }
//
//
//    private static void buildMysqlJob(CdcJobProperties job, DatasourceProperties datasourceProperties) {
//        log.info("开始启动Flink CDC获取{}变更数据......", JSONUtil.toJsonStr(job));
//    }
//}
//
//
