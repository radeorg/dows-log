package org.dows.log.biz.admin;

import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.utils.BeanConvert;
import org.dows.log.api.admin.request.ListLogTableRequest;
import org.dows.log.api.admin.request.SaveLogTableRequest;
import org.dows.log.api.admin.response.ListLogTableResponse;
import org.dows.log.api.admin.response.LogTableInfoResponse;
import org.dows.log.mysql.entity.LogTableEntity;
import org.dows.log.mysql.repository.LogTableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* @description project descr:应用api:日志表
*
* @author lait.zhang
* @date 2024年4月20日 上午10:44:44
*/
@RequiredArgsConstructor
@Service
public class LogTableBiz {
    private final LogTableRepository logTableRepository;

    public void save(List<SaveLogTableRequest> saveLogTable) {
        List<LogTableEntity> logTableEntities = BeanConvert.beanConvert(saveLogTable, LogTableEntity.class);
        logTableRepository.saveOrUpdateBatch(logTableEntities);
    }

    public List<ListLogTableResponse> list(ListLogTableRequest listLogTable) {
        // 实现查询日志表列表的逻辑
        List<LogTableEntity> logTableEntities = logTableRepository.list();
        return BeanConvert.beanConvert(logTableEntities, ListLogTableResponse.class);
    }

    public List<ListLogTableResponse> page(ListLogTableRequest listLogTable) {
        // 实现分页查询日志表列表的逻辑
//        Page<LogTableEntity> page = logTableRepository.lambdaQuery().page(listLogTable.toPage());
//        List<ListLogTableResponse> listLogTableResponses = BeanConvert.beanConvert(page.getRecords(), ListLogTableResponse.class);
//        return listLogTableResponses;
        return new ArrayList<ListLogTableResponse>();
    }

    public LogTableInfoResponse getById(Long logTableId) {
        LogTableEntity logTableEntity = logTableRepository.getById(logTableId);
        return BeanConvert.beanConvert(logTableEntity, LogTableInfoResponse.class);
    }

    public void deleteByIds(List<Long> logTableIds) {
        logTableRepository.removeByIds(logTableIds);
    }
}