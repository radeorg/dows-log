package org.dows.log.mysql.repository;

import org.dows.framework.crud.mybatis.MybatisRepositoryImpl;
import org.dows.log.mysql.entity.LogActionEntity;
import org.dows.log.mysql.entity.LogColumnEntity;
import org.dows.log.mysql.mapper.LogActionMapper;
import org.dows.log.mysql.mapper.LogColumnMapper;
import org.springframework.stereotype.Service;


/**
 * 日志列(LogColumn)表操作接口
 *
 * @author lait
 * @since 2024-04-20 10:54:45
 */
@Service
public class LogActionRepository extends MybatisRepositoryImpl<LogActionMapper, LogActionEntity> {

}

