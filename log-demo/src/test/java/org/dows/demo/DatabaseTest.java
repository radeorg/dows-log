package org.dows.demo;

import cn.hutool.json.JSONUtil;
import org.dows.log.Column;
import org.dows.log.Database;
import org.dows.log.DatabaseProperties;
import org.dows.log.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class DatabaseTest {
    @Autowired
    private DatabaseProperties databaseProperties;

    @Test
    public void test() {

        List<Database> databases = databaseProperties.getDatabases();

        for (Database database : databases) {
            List<Table> tables = database.getTables();
            for (Table table : tables) {
                List<Column> list = table.getColumns().stream().filter(Column::isSelected).toList();
                System.out.println(JSONUtil.toJsonPrettyStr(list));
            }
        }

        //System.out.println(JSONUtil.toJsonPrettyStr(databases));

    }

}

