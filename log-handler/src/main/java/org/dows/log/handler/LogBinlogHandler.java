package org.dows.log.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.cdc.CdcMessage;
import org.dows.log.api.util.JsonPathUtil;
import org.dows.log.config.LogConfig;
import org.dows.log.event.BinLogEvent;
import org.dows.log.model.CellData;
import org.dows.log.model.Column;
import org.dows.log.model.RowData;
import org.dows.log.mongo.BinlogRepository;
import org.dows.log.mysql.repository.LogSettingRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LogBinlogHandler {
    private final LogConfig logConfig;
    private final LogSettingRepository logSettingRepository;
    private final BinlogRepository binLogRepository;

    @PostConstruct
    public void init() {
        log.info("init binlog handler");
    }

    //@Async
//    @EventListener(value = CdcMessage.class)
    public void handle(CdcMessage event) {
//        try {
//            log.info("====== {}", JSONUtil.toJsonStr(event));
//            String logString = (String) event.getSource();
////            Map<String,Object> map1 = JsonPathUtil.readMap(logString, "$.after");
//            // 将value转为json对象
//            JSONObject entries = JSONUtil.parseObj(logString);
//            Object source = entries.get("source");
//            Object before = entries.get("before");
//            Object after = entries.get("after");
//            if ("null".equals(before.toString()) || "null".equals(after.toString())) {
//                return;
//            }
//            Map<String, Object> beforeMap = (Map) before;
//            Map<String, Object> afterMap = (Map) after;
//            Set<String> set1 = afterMap.keySet();
//            // 将source转为json对象
//            JSONObject sourceJson = JSONUtil.parseObj(source.toString());
//            String db = (String) sourceJson.get("db");
//            String table = (String) sourceJson.get("table");
//            List<Column> columns = logSettingRepository.selectByTableName(logConfig.getAppName(), db, table);
//            List<CellData> list = new ArrayList<>();
//            if (columns == null || columns.isEmpty()) {
//                return;
//            }
//            List<String> collect = columns.stream().map(Column::getName).toList();
//            Set<String> difference = set1.stream()
//                    .filter(item -> !collect.contains(item))
//                    .collect(Collectors.toSet());
//            for (String s : difference) {
//                if (!"account_id".equals(s)) {
//                    // 去除数据库配置不存在keys
//                    afterMap.remove(s);
//                }
//            }
//            for (Column column : columns) {
//                String name = column.getName();
//                Object bval = beforeMap.get(name);
//                Object aval = afterMap.get(name);
//                if (aval == null || bval.equals(aval)) {
//                    afterMap.remove(name);
//                }
//            }
//            String tableName = JsonPathUtil.readStr(logString, "$.source.table");
//            Object key = beforeMap.get(tableName + "_id");
////            Long key = JsonPathUtil.readLong(logString, "$.before." + tableName + "_id");
//            RowData rowData = RowData.builder()
//                    .after(CollectionUtil.newArrayList(afterMap))
//                    .id(db + ":" + tableName + ":" + key)
//                    .build();
////            Map<String, Map<String, List<CellData>>> result = new HashMap<>();
//            Map<String, List<CellData>> map = new HashMap<>();
////            Long time = (Long)entries.get("ts_ms");
//            afterMap.put("ts_ms", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
////            map.put(time.toString(),list);
////            result.put(key.toString(),map);
//            RowData rowData1 = binLogRepository.find("_id", db + ":" + tableName + ":" + key, logConfig.getBinlogCollectionName() + tableName);
//            if (rowData1 != null) {
//                binLogRepository.addItemsToDocument(db + ":" + tableName + ":" + key, afterMap, logConfig.getBinlogCollectionName() + tableName);
//            } else {
//
//                binLogRepository.save(rowData, logConfig.getBinlogCollectionName() + tableName);
//            }
//        } catch (Exception e) {
//            log.error("binlog handler error: {}", e.getMessage());
//        }


    }
}
