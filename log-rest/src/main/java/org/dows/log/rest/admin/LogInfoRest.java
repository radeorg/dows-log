package org.dows.log.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.annotation.Namespace;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListBinLogRequest;
import org.dows.log.api.admin.request.ListRequestLogRequest;
import org.dows.log.api.annotation.Actlog;
import org.dows.log.biz.admin.LogBiz;
import org.dows.log.api.admin.request.RequestLog;
import org.dows.log.model.RowData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Namespace(module = "log", name = "日志管理", code = "log.logInfo", path = "/")
@RequiredArgsConstructor
@RestController
@Tag(name = "binlog 请求", description = "binlog 请求")
public class LogInfoRest {
    private final LogBiz binLogBiz;

    @Operation(summary = "分页查询binlog列表")
    @PostMapping("v1/admin/binLog/page")
    @Actlog
    public PageResponse<RowData> binLogPage(@RequestBody PageRequest<ListBinLogRequest> listBinLogRequest) {
        return binLogBiz.binLogPage(listBinLogRequest);
    }


    @Operation(summary = "分页查询requestLogPage列表")
    @PostMapping("v1/admin/requestLog/page")
    @Actlog
    public PageResponse<RequestLog> requestLogPage(@RequestBody PageRequest<ListRequestLogRequest> listRequestLogRequestPageRequest) {
        return binLogBiz.requestLogPage(listRequestLogRequestPageRequest);
    }
}
