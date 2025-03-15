package org.dows.log.mysql.repository;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.MybatisRepositoryImpl;
import org.dows.log.model.Column;
import org.dows.log.model.Table;
import org.dows.log.mysql.entity.LogSettingEntity;
import org.dows.log.mysql.mapper.LogSettingMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 日志配置(LogSetting)表操作接口
 *
 * @author lait
 * @since 2024-04-20 10:54:46
 */
@RequiredArgsConstructor
@Service
public class LogSettingRepository extends MybatisRepositoryImpl<LogSettingMapper, LogSettingEntity> {
    private final LogSettingMapper logSettingMapper;


    @Cacheable(value = "selectByTableName", key = "#id+'-'+#databaseName+'-'+#tableName")
    public List<Column> selectByTableName(String appId, String databaseName, String tableName){
        String s = logSettingMapper.selectByTableName(appId, databaseName, tableName);
        Table bean = JSONUtil.toBean(s, Table.class);
        return bean.getColumns();
    }
}

