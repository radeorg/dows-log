package org.dows.log.biz.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.utils.BeanConvert;
import org.dows.log.api.admin.request.ListLogColumnRequest;
import org.dows.log.api.admin.request.SaveLogColumnRequest;
import org.dows.log.api.admin.response.ListLogColumnResponse;
import org.dows.log.api.admin.response.LogColumnInfoResponse;
import org.dows.log.mysql.entity.LogColumnEntity;
import org.dows.log.mysql.repository.LogColumnRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lait.zhang
 * @description project descr:应用api:日志列
 * @date 2024年4月20日 上午10:44:44
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LogColumnBiz {
    private final LogColumnRepository logColumnRepository;

    public void save(List<SaveLogColumnRequest> saveLogColumn) {
        List<LogColumnEntity> logColumnEntities = BeanConvert.beanConvert(saveLogColumn, LogColumnEntity.class);
        logColumnRepository.saveOrUpdateBatch(logColumnEntities);
        log.info("logColumnEntities {}", logColumnEntities.stream().map(LogColumnEntity::getLogColumnId).toList());
    }

    public List<ListLogColumnResponse> list(ListLogColumnRequest listLogColumn) {
        // 实现查询日志列列表的逻辑
        List<LogColumnEntity> logColumnEntities = logColumnRepository.list();
        return BeanConvert.beanConvert(logColumnEntities, ListLogColumnResponse.class);
    }

    public List<ListLogColumnResponse> page(ListLogColumnRequest listLogColumn) {
        // 实现分页查询日志列列表的逻辑
//        Page<LogColumnEntity> page = logColumnRepository.lambdaQuery().page(listLogColumn.toPage());
//        List<ListLogColumnResponse> listLogColumnResponses = BeanConvert.beanConvert(page.getRecords(), ListLogColumnResponse.class);
//        return listLogColumnResponses;
        return new ArrayList<>();
    }

    public LogColumnInfoResponse getById(Long logColumnId) {
        LogColumnEntity logColumnEntity = logColumnRepository.getById(logColumnId);
        return BeanConvert.beanConvert(logColumnEntity, LogColumnInfoResponse.class);
    }

    public void deleteByIds(List<Long> logColumnIds) {
        logColumnRepository.removeByIds(logColumnIds);
    }


}