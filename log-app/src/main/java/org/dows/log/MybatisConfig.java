package org.dows.log;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"org.dows.log.mysql.mapper"}, annotationClass = Mapper.class)
public class MybatisConfig {

    /**
     * 分页插件
     */

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }




    /*@PostConstruct
    public void init() throws Exception {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("192.168.111.103")
                .port(13306)
                .username("root")
                .password("shdy123!")
                .databaseList("wes_all")
                .tableList("wes_all.account_instance")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .includeSchemaChanges(true)
                .build();
        System.out.println("init data source");

        *//*Configuration configuration = new Configuration();
        configuration.setInteger(RestOptions.PORT, 8081);*//*

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000);
        env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MYSQL Source").addSink(customSink());
        env.execute();

        System.out.println("env start");
    }*/

}