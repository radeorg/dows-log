package org.dows.log.mysql.mapper;

import cn.hutool.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.framework.crud.mybatis.MybatisMapper;
import org.dows.log.mysql.entity.LogColumnEntity;

/**
 * 日志列(LogColumn)表数据库访问层
 *
 * @author lait
 * @since 2024-04-20 10:54:45
 */
@Mapper
public interface LogColumnMapper extends MybatisMapper<LogColumnEntity> {

}

