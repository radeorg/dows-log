package org.dows.log.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.framework.crud.mybatis.MybatisMapper;
import org.dows.log.mysql.entity.LogSettingEntity;

/**
 * 日志配置(LogSetting)表数据库访问层
 *
 * @author lait
 * @since 2024-04-20 10:54:46
 */
@Mapper
public interface LogSettingMapper extends MybatisMapper<LogSettingEntity> {
    @Select("select column_json from log_setting left join log_table on log_setting.log_setting_id = log_table.log_setting_id left join log_column on log_table.log_table_id = log_column.log_table_id  where log_setting.app_id = #{appId} and database_name = #{databaseName} and table_name = #{tableName} ")
    String selectByTableName(@Param("appId") String appId, @Param("databaseName") String databaseName, @Param("tableName") String tableName);
}

