package org.dows.log.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.annotation.Namespace;
import org.dows.log.api.admin.request.ListLogTableRequest;
import org.dows.log.api.admin.request.SaveLogTableRequest;
import org.dows.log.api.admin.response.ListLogTableResponse;
import org.dows.log.api.admin.response.LogTableInfoResponse;
import org.dows.log.api.annotation.Actlog;
import org.dows.log.biz.admin.LogTableBiz;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @description project descr:应用api:日志表
*
* @author lait.zhang
* @date 2024年4月20日 上午10:44:44
*/
@Namespace(module = "log", name = "日志表", code = "log.logTable", path = "/")
@RequiredArgsConstructor
@RestController
@Tag(name = "日志表", description = "日志表")
public class LogTableRest {
    private final LogTableBiz logTableBiz;

    /**
    * 创建或更新日志表
    * @param
    * @return
    */
    @Operation(summary = "创建或更新日志表")
    @PostMapping("v1/admin/logTable/save")
    @Actlog
    public void save(@RequestBody @Validated List<SaveLogTableRequest> saveLogTable ) {
        logTableBiz.save(saveLogTable);
    }

    /**
    * 查询日志表列表
    * @param
    * @return
    */
    @Operation(summary = "查询日志表列表")
    @PostMapping("v1/admin/logTable/list")
    @Actlog
    public List<ListLogTableResponse> list(@RequestBody @Validated ListLogTableRequest listLogTable) {
        return logTableBiz.list(listLogTable);
    }

    /**
    * 分页查询日志表列表
    * @param
    * @return
    */
    @Operation(summary = "分页查询日志表列表")
    @PostMapping("v1/admin/logTable/page")
    @Actlog
    public List<ListLogTableResponse> page(@RequestBody @Validated ListLogTableRequest listLogTable) {
        return logTableBiz.page(listLogTable);
    }

    /**
    * 查询日志表详情
    * @param
    * @return
    */
    @Operation(summary = "查询日志表详情")
    @GetMapping("v1/admin/logTable/getById")
    @Actlog
    public LogTableInfoResponse getById(@RequestParam Long logTableId) {
        return logTableBiz.getById(logTableId);
    }

    /**
    * 删除
    * @param
    * @return
    */
    @Operation(summary = "删除")
    @DeleteMapping("v1/admin/logTable/deleteById")
    @Actlog
    public void deleteByIds(@RequestParam List<Long> logTableIds) {
        logTableBiz.deleteByIds(logTableIds);
    }


}