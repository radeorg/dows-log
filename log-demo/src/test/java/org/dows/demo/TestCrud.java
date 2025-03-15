package org.dows.demo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.extern.slf4j.Slf4j;
import org.dows.log.ColumnLog;
import org.dows.log.DataLog;
import org.dows.log.DatabaseProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

@Slf4j
@SpringBootTest
public class TestCrud {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DatabaseProperties databaseProperties;

    Map<String, ? extends Model> logModel = new HashMap<>();

    public List<JSONObject> queryLog(String queryTable, Map<String, Object> queryParams) {
        Model model = logModel.get(queryTable);

        if (model != null) {
            QueryWrapper<?> wrapper = new QueryWrapper<>();
            //wrapper.eq();
            return model.selectList(wrapper);

        }
        return Collections.emptyList();
    }

    @Test
    public void userEntityLogQuery() {


    }


    @Test
    public void insertCustomCollection() throws Exception {
//        List<Database> databases = databaseProperties.getDatabases();
////        UserEntity person =new UserEntity();
////        Random random = new Random(100);
////        person.setId(random.nextInt());
////        person.setUserName("张三");
////        mongoTemplate.insert(databases, "data_log");
////        List<Database> byId = mongoTemplate.findById("6620e013a721240b076e8fab", List.class);
//
//        System.out.println(byId);
        DataLog dataLog = new DataLog();
        Map<String,Map<String,List<ColumnLog>>> amp = new HashMap<>();
        Map<String,List<ColumnLog>> map = new HashMap<>();

        List<ColumnLog> columnLogs = new ArrayList<>();

        ColumnLog columnLog = new ColumnLog();
        columnLog.setColumn("name");
        columnLog.setBefore("aaa");
        columnLog.setAfter("bbb");
        columnLog.setAccount("admin");
        columnLog.setTimestamp(new Date());
        columnLogs.add(columnLog);
        ColumnLog columnLog2 = new ColumnLog();

        columnLog2.setColumn("nikeName");
        columnLog2.setBefore("aaa");
        columnLog2.setAfter("bbb");
        columnLog2.setAccount("admin");
        columnLog2.setTimestamp(new Date());

        columnLogs.add(columnLog2);

        List<ColumnLog> columnLogs2 = new ArrayList<>();

        ColumnLog columnLog21 = new ColumnLog();
        columnLog21.setColumn("name");
        columnLog21.setBefore("aaa");
        columnLog21.setAfter("bbb");
        columnLog21.setAccount("admin");
        columnLog21.setTimestamp(new Date());
        columnLogs2.add(columnLog21);
        ColumnLog columnLog22 = new ColumnLog();

        columnLog22.setColumn("nikeName");
        columnLog22.setBefore("aaa");
        columnLog22.setAfter("bbb");
        columnLog22.setAccount("admin");
        columnLog22.setTimestamp(new Date());

        columnLogs2.add(columnLog22);

        map.put(new Date().toString(), columnLogs);
        Thread.sleep(200);
        map.put(new Date().toString(), columnLogs2);
        amp.put("222",map);

        dataLog.setDatabaseAndTable(amp);
        log.info(JSONUtil.toJsonPrettyStr(dataLog));
        mongoTemplate.insert(dataLog, "shdy:user");
    }
}
