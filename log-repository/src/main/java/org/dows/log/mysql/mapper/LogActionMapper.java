package org.dows.log.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisMapper;
import org.dows.log.mysql.entity.LogActionEntity;
import org.dows.log.mysql.entity.LogColumnEntity;

/**
 * 日志列(LogColumn)表数据库访问层
 *
 * @author lait
 * @since 2024-04-20 10:54:45
 */
@Mapper
public interface LogActionMapper extends MybatisMapper<LogActionEntity> {

}

