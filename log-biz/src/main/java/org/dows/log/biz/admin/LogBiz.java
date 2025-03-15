package org.dows.log.biz.admin;

import lombok.RequiredArgsConstructor;
import org.dows.app.api.AppContext;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListBinLogRequest;
import org.dows.log.api.admin.request.ListRequestLogRequest;
import org.dows.log.api.admin.request.RequestLog;
import org.dows.log.handler.LogActlogHandler;
import org.dows.log.model.RowData;
import org.dows.log.mongo.ActlogRepository;
import org.dows.log.mongo.BinlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogBiz {

    private final BinlogRepository binLogRepository;
    private final ActlogRepository actlogRepository;
    private final LogActlogHandler logActlogHandler;

    public PageResponse<RowData> binLogPage(PageRequest<ListBinLogRequest> listBinLogRequest) {
        return binLogRepository.findPaginatedDocuments(listBinLogRequest);
    }

    public PageResponse<RequestLog> requestLogPage(PageRequest<ListRequestLogRequest> listBinLogRequest) {
        String appId = AppContext.getAppId();
        listBinLogRequest.getQueryObject().setCollectionName(logActlogHandler.getCollectionName(appId));
        listBinLogRequest.getQueryObject().setAppId(appId);
        return actlogRepository.getByCondition(listBinLogRequest);
    }
}
