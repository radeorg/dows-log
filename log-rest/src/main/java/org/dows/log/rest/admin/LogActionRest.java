package org.dows.log.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.annotation.Namespace;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListLogActionRequest;
import org.dows.log.api.admin.response.ListLogActionResponse;
import org.dows.log.api.admin.response.LogActionInfoResponse;
import org.dows.log.api.annotation.Actlog;
import org.dows.log.biz.admin.LogActionBiz;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/24/2024 9:52 AM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Namespace(module = "log", name = "请求日志", code = "log.logAction", path = "/")
@RequiredArgsConstructor
@RestController
@Tag(name = "请求日志", description = "请求日志")
public class LogActionRest {
    private final LogActionBiz logActionBiz;


//    @Operation(summary = "创建或更新日志列")
//    @PostMapping("v1/admin/LogAction/save")
//    @Actlog
//    public void save(@RequestBody @Validated List<SaveLogActionRequest> saveLogAction ) {
//        logActionBiz.save(saveLogAction);
//    }

    /**
     * 查询日志列列表
     * @param
     * @return
     */
    @Operation(summary = "查询日志列列表")
    @PostMapping("v1/admin/logAction/list")
    @Actlog
    public List<ListLogActionResponse> list(@RequestBody @Validated ListLogActionRequest listLogAction) {
        return logActionBiz.list(listLogAction);
    }

    /**
     * 分页查询日志列列表
     * @param
     * @return
     */
    @Operation(summary = "分页查询日志列列表")
    @PostMapping("v1/admin/logAction/page")
    @Actlog
    public PageResponse<ListLogActionResponse> page(@RequestBody @Validated PageRequest<ListLogActionRequest> listLogAction) {
        return logActionBiz.page(listLogAction);
    }

    /**
     * 查询日志列详情
     * @param
     * @return
     */
    @Operation(summary = "查询日志列详情")
    @GetMapping("v1/admin/logAction/getById")
    @Actlog
    public LogActionInfoResponse getById(@RequestParam Long logActionId) {
        return logActionBiz.getById(logActionId);
    }

    /**
     * 删除
     * @param
     * @return
     */
    @Operation(summary = "删除")
    @DeleteMapping("v1/admin/logAction/deleteById")
    @Actlog
    public void deleteByIds(@RequestParam List<Long> logActionIds) {
        logActionBiz.deleteByIds(logActionIds);
    }
}

