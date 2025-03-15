package org.dows.log.biz.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dows.app.api.AppContext;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.framework.crud.mybatis.utils.BeanConvert;
import org.dows.log.api.LogActionApi;
import org.dows.log.api.admin.request.ListLogActionRequest;
import org.dows.log.api.admin.request.ListRequestLogRequest;
import org.dows.log.api.admin.request.SaveLogActionRequest;
import org.dows.log.api.admin.response.ListLogActionResponse;
import org.dows.log.api.admin.response.LogActionInfoResponse;
import org.dows.log.api.admin.request.RequestLog;
import org.dows.log.handler.LogActlogHandler;
import org.dows.log.mongo.ActlogRepository;
import org.dows.log.mysql.entity.LogActionEntity;
import org.dows.log.mysql.repository.LogActionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LogActionBiz implements LogActionApi {
    private final LogActionRepository logActionRepository;

    private final ActlogRepository actlogRepository;
    private final LogActlogHandler logActlogHandler;

    @Override
    public void save(List<SaveLogActionRequest> logActionRequests) {

        List<LogActionEntity> logActionEntities = BeanConvert.beanConvert(logActionRequests, LogActionEntity.class);
        List<LogActionEntity> insertList = new ArrayList<>();
        List<LogActionEntity> updateList = new ArrayList<>();
        for (LogActionEntity logActionEntity : logActionEntities) {
            List<LogActionEntity> list1 = logActionRepository.lambdaQuery()
                    .eq(LogActionEntity::getSignature, logActionEntity.getSignature()).list();
            if (CollectionUtil.isNotEmpty(list1)) {
                updateList.addAll(list1);
            } else {
                insertList.add(logActionEntity);
            }
        }
        if( CollectionUtil.isNotEmpty(insertList)){
            logActionRepository.saveBatch(insertList);
        }
        if( CollectionUtil.isNotEmpty(updateList)){
            logActionRepository.updateBatchById(updateList);
        }
    }

    @Override
    @Cacheable(value = "log:action", key = "#method+':'+#action")
    public Boolean logEnable(String signatrue) {
        if (StrUtil.isBlank(signatrue)) {
            return false;
        }
        List<LogActionEntity> list = logActionRepository.lambdaQuery()
                .eq(LogActionEntity::getSignature, signatrue)
                .list();
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0).getEnable();
        }
        return false;
    }


    public List<ListLogActionResponse> list(ListLogActionRequest listLogAction) {
        List<LogActionEntity> list = logActionRepository.lambdaQuery()
                .eq(Objects.nonNull(listLogAction.getAction()), LogActionEntity::getAction, listLogAction.getAction())
                .like(Objects.nonNull(listLogAction.getDescr()), LogActionEntity::getDescr, listLogAction.getDescr())
                .eq(Objects.nonNull(listLogAction.getMethod()), LogActionEntity::getMethod, listLogAction.getMethod())
                .eq(Objects.nonNull(listLogAction.getUri()), LogActionEntity::getUri, listLogAction.getUri())
                .eq(Objects.nonNull(listLogAction.getSignature()), LogActionEntity::getSignature, listLogAction.getSignature())
                .eq(Objects.nonNull(listLogAction.getLogActionId()), LogActionEntity::getLogActionId, listLogAction.getLogActionId())
                .list();

        return BeanConvert.beanConvert(list, ListLogActionResponse.class);
    }

    public PageResponse<ListLogActionResponse> page(PageRequest<ListLogActionRequest> listLogAction) {
        ListLogActionRequest queryObject = listLogAction.getQueryObject();
        Page<LogActionEntity> page = logActionRepository.lambdaQuery()
                .eq(Objects.nonNull(queryObject.getAction()), LogActionEntity::getAction, queryObject.getAction())
                .like(Objects.nonNull(queryObject.getDescr()), LogActionEntity::getDescr, queryObject.getDescr())
                .eq(Objects.nonNull(queryObject.getMethod()), LogActionEntity::getMethod, queryObject.getMethod())
                .eq(Objects.nonNull(queryObject.getUri()), LogActionEntity::getUri, queryObject.getUri())
                .eq(Objects.nonNull(queryObject.getSignature()), LogActionEntity::getSignature, queryObject.getSignature())
                .eq(Objects.nonNull(queryObject.getLogActionId()), LogActionEntity::getLogActionId, queryObject.getLogActionId())
                .page(listLogAction.toPage());
        List<ListLogActionResponse> listLogActionResponses = BeanConvert.beanConvert(page.getRecords(), ListLogActionResponse.class);
        Page<ListLogActionResponse> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(listLogActionResponses);
        return new PageResponse<>(result);
    }

    public LogActionInfoResponse getById(Long logActionId) {

        LogActionEntity logActionEntity = logActionRepository.getById(logActionId);
        if (Objects.nonNull(logActionEntity)) {
            return BeanConvert.beanConvert(logActionEntity, LogActionInfoResponse.class);
        }
        return null;
    }

    public void deleteByIds(List<Long> logActionIds) {

        logActionRepository.removeByIds(logActionIds);
    }

    public PageResponse<RequestLog> requestLogPage(PageRequest<ListRequestLogRequest> listBinLogRequest) {
        listBinLogRequest.getQueryObject().setCollectionName(logActlogHandler.getCollectionName(AppContext.getAppId()));
        return actlogRepository.getByCondition(listBinLogRequest);
    }
}
