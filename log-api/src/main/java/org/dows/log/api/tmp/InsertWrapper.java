//package org.dows.log.api.tmp;
//
//import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Constants;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class InsertWrapper<T> extends AbstractWrapper<T, String, InsertWrapper<T>> implements Insert<InsertWrapper<T>, String> {
//
//    private Map<String, String> sqlMap;
//
//    public InsertWrapper() {
//        this.sqlMap = new HashMap<>();
//        super.initNeed();
//    }
//
//    @Override
//    protected InsertWrapper<T> instance() {
//        return new InsertWrapper();
//    }
//
//    @Override
//    public InsertWrapper<T> set(boolean condition, String column, Object val, String mapping) {
//        return this.maybeDo(condition, () -> {
//            String sql = this.formatParam(mapping, val);
//            this.sqlMap.put(column, sql);
//        });
//    }
//
//    @Override
//    public InsertWrapper<T> setMap(Map<String, String> map) {
//        this.sqlMap = new HashMap<>();
//        for (String key : map.keySet()) {
//            String sql = this.formatParam(null, map.get(key));
//            this.sqlMap.put(key, sql);
//        }
//        return this.typedThis;
//    }
//
//    @Override
//    public String getSql() {
//        this.sqlMap.entrySet().removeIf(entry -> entry.getValue() == null);
//        long time = System.currentTimeMillis();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("(");
//        //Set<String> keys = this.sqlMap.keySet().stream().map(s -> "`"+s+"`").distinct().collect(Collectors.toSet());
//        stringBuilder.append(String.join(Constants.COMMA, this.sqlMap.keySet()));
//        stringBuilder.append(")");
//        stringBuilder.append("values");
//        stringBuilder.append("(");
//        //Set<String> values = this.sqlMap.values().stream().map(s -> "\""+s+"\"").distinct().collect(Collectors.toSet());
//        stringBuilder.append(String.join(Constants.COMMA, this.sqlMap.values()));
//        stringBuilder.append(")");
//        log.info(stringBuilder.toString());
//        log.info(System.currentTimeMillis() - time + "ms");
//        return stringBuilder.toString();
//    }
//
//
//}
