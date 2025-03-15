package org.dows.log.mysql.repository;

import org.dows.framework.crud.mybatis.MybatisRepositoryImpl;
import org.dows.log.mysql.entity.LogTableEntity;
import org.dows.log.mysql.mapper.LogTableMapper;
import org.springframework.stereotype.Service;


/**
 * 日志表(LogTable)表操作接口
 *
 * @author lait
 * @since 2024-04-20 10:54:46
 */
@Service
public class LogTableRepository extends MybatisRepositoryImpl<LogTableMapper, LogTableEntity> {

}

