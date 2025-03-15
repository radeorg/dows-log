package org.dows.log.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisMapper;
import org.dows.log.mysql.entity.LogTableEntity;

/**
 * 日志表(LogTable)表数据库访问层
 *
 * @author lait
 * @since 2024-04-20 10:54:46
 */
@Mapper
public interface LogTableMapper extends MybatisMapper<LogTableEntity> {

}

