package org.dows.log;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.dows.framework.api.uim.AccountInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Primary
@Component(value = "fillHandler")
@Slf4j
public class FillHandler implements MetaObjectHandler {
    private final Map<String, Class<?>> fieldTypMap = new HashMap<>();
    @PostConstruct
    public void init() {
        fieldTypMap.put("deleted", Boolean.class);
        fieldTypMap.put("dt", Date.class);
        fieldTypMap.put("updateDt", Date.class);
        fieldTypMap.put("name", String.class);
        fieldTypMap.put("label", String.class);
        fieldTypMap.put("title", String.class);
        fieldTypMap.put("appId", String.class);
        fieldTypMap.put("pycode", String.class);
        fieldTypMap.put("accountId", Long.class);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        String snowflakeIdName = Introspector.decapitalize((metaObject.getOriginalObject().getClass().getSimpleName() + "Id")).replace("Instance", "").replace("Entity", "");
        if (metaObject.hasSetter(snowflakeIdName)) {
            Object o = metaObject.getValue(snowflakeIdName);
            if (o == null) {
                fillStrategy(metaObject, snowflakeIdName, IdWorker.getIdStr());
            }
        }
        fillField(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillField(metaObject);
    }

    private void fillField(MetaObject metaObject) {
        fieldTypMap.forEach((k, v) -> fillField(metaObject, k, v));
    }

    private void fillField(MetaObject metaObject, String k, Class<?> v) {
        if (metaObject.hasSetter(k)) {
            Object o = metaObject.getValue(k);
            if (o == null && v.getName().equals("java.lang.Boolean")) {
                fillStrategy(metaObject, k, false);
            } else if (o == null && v.getName().equals("java.util.Date")) {
                fillStrategy(metaObject, k, new Date());
            } else if (k.equalsIgnoreCase("appId")) {
                //this.setFieldValByName(k, "1", metaObject);
            } else if (k.equalsIgnoreCase("accountId")) {
//                AccountInfo principal = (AccountInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                this.setFieldValByName(k, principal.getId(), metaObject);
            } else if (k.equalsIgnoreCase("pycode")) {
//                String pyf = ClassUtil.getDeclaredField(metaObject.getOriginalObject().getClass(), "pycode")
//                        .getAnnotation(Pinyin.class).value();
//                Object value = metaObject.getValue(pyf);
//                if (value != null) {
//                    try {
////                    String value = AnnotationUtil
////                            .getAnnotationValue(metaObject.getOriginalObject().getClass(), Pinyin.class, k);
//                        setFieldValByName(k, PinyinHelper.getShortPinyin(value.toString()), metaObject);
//                    } catch (PinyinException e) {
//                        log.error(e.getMessage());
//                    }
//                }
            }

        }
    }


}
