package org.dows.log.biz.admin;

import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.utils.BeanConvert;
import org.dows.log.api.admin.request.ListLogSettingRequest;
import org.dows.log.api.admin.request.SaveLogSettingRequest;
import org.dows.log.api.admin.response.ListLogSettingResponse;
import org.dows.log.api.admin.response.LogSettingResponse;
import org.dows.log.mysql.entity.LogSettingEntity;
import org.dows.log.mysql.repository.LogSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* @description project descr:应用api:日志配置
*
* @author lait.zhang
* @date 2024年4月20日 上午10:44:44
*/
@RequiredArgsConstructor
@Service
public class LogSettingBiz {
    private final LogSettingRepository logSettingRepository;

    public void save(List<SaveLogSettingRequest> saveLogSetting) {
        List<LogSettingEntity> logSettingEntities = BeanConvert.beanConvert(saveLogSetting, LogSettingEntity.class);
        logSettingRepository.saveOrUpdateBatch(logSettingEntities);
    }

    public List<ListLogSettingResponse> list(ListLogSettingRequest listLogSetting) {
        // 实现查询日志配置列表的逻辑
        List<LogSettingEntity> logSettingEntities = logSettingRepository.list();
        return BeanConvert.beanConvert(logSettingEntities, ListLogSettingResponse.class);
    }

    public ListLogSettingResponse page(ListLogSettingRequest listLogSetting) {
        // 实现分页查询日志配置列表的逻辑
//        Page<LogSettingEntity> page = logSettingRepository.lambdaQuery().page(listLogSetting.toPage());
//        List<ListLogSettingResponse> listLogSettingResponses = BeanConvert.beanConvert(page.getRecords(), ListLogSettingResponse.class);
//        return listLogSettingResponses;
        return null;
    }

    public LogSettingResponse getById(Long logSettingId) {
        LogSettingEntity logSettingEntity = logSettingRepository.getById(logSettingId);
        return BeanConvert.beanConvert(logSettingEntity, LogSettingResponse.class);
    }

    public void deleteByIds(List<Long> logSettingIds) {
        logSettingRepository.removeByIds(logSettingIds);
    }

}