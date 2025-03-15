package org.dows.log.api.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.jayway.jsonpath.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description JsonPath 工具类
 * @date 2023/11/8 11:20
 **/
public class JsonPathUtil {
    public static final String PATH_NULL = "$.null";
    private static final Configuration conf;
    private static final ParseContext parseContext;

    static {
        conf = Configuration.defaultConfiguration();
        parseContext = JsonPath.using(conf);
    }

    public static String readStr(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return (String) read(ctx, jsonPath);
    }

    public static Integer readInteger(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return (Integer) read(ctx, jsonPath);
    }

    public static Long readLong(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return (Long) read(ctx, jsonPath);
    }

    public static Object readObj(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return read(ctx, jsonPath);
    }

    public static Map readMap(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return (Map) read(ctx, jsonPath);
    }

    public static List readList(String source, String jsonPath) {
        DocumentContext ctx = parseContext.parse(source);
        return (List) read(ctx, jsonPath);
    }

    public static Map<String, Object> readMap(String source, Set<String> extractSet) {
        if (CollUtil.isEmpty(extractSet)) {
            return Collections.emptyMap();
        }
        if (StrUtil.isBlank(source)) {
            return extractSet.stream()
                    .collect(Collectors.toMap(key -> key, key -> null));
        }

        DocumentContext ctx = parseContext.parse(source);
        return extractSet.stream()
                .filter(item -> read(ctx, item) != null)
                .collect(Collectors.toMap(item -> item,
                        item -> read(ctx, item),
                        (v1, v2) -> v2,
                        LinkedHashMap::new));
    }

    public static Object read(DocumentContext ctx, String jsonPath, Object value) {
        Object result;
        try {
            result = ctx.read(jsonPath);
        } catch (PathNotFoundException e) {
            if (Objects.nonNull(value)) {
                result = value;
            } else {
                result = null;
            }
        }
        return result;
    }

    public static Object read(DocumentContext ctx, String jsonPath) {
        return read(ctx, jsonPath, null);
    }
}