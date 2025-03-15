package org.dows.log.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.annotation.Namespace;
import org.dows.log.api.admin.request.ListLogColumnRequest;
import org.dows.log.api.admin.request.SaveLogColumnRequest;
import org.dows.log.api.admin.response.ListLogColumnResponse;
import org.dows.log.api.admin.response.LogColumnInfoResponse;
import org.dows.log.api.annotation.Actlog;
import org.dows.log.biz.admin.LogColumnBiz;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @description project descr:应用api:日志列
*
* @author lait.zhang
* @date 2024年4月20日 上午10:44:44
*/
@Namespace(module = "log", name = "日志列", code = "log.logColumn", path = "/")
@RequiredArgsConstructor
@RestController
@Tag(name = "日志列", description = "日志列")
public class LogColumnRest {
    private final LogColumnBiz logColumnBiz;

    /**
    * 创建或更新日志列
    * @param
    * @return
    */
    @Operation(summary = "创建或更新日志列")
    @PostMapping("v1/admin/logColumn/save")
    @Actlog
    public void save(@RequestBody @Validated List<SaveLogColumnRequest> saveLogColumn ) {
        logColumnBiz.save(saveLogColumn);
    }

    /**
    * 查询日志列列表
    * @param
    * @return
    */
    @Operation(summary = "查询日志列列表")
    @PostMapping("v1/admin/logColumn/list")
    @Actlog
    public List<ListLogColumnResponse> list(@RequestBody @Validated ListLogColumnRequest listLogColumn) {
        return logColumnBiz.list(listLogColumn);
    }

    /**
    * 分页查询日志列列表
    * @param
    * @return
    */
    @Operation(summary = "分页查询日志列列表")
    @PostMapping("v1/admin/logColumn/page")
    @Actlog
    public List<ListLogColumnResponse> page(@RequestBody @Validated ListLogColumnRequest listLogColumn) {
        return logColumnBiz.page(listLogColumn);
    }

    /**
    * 查询日志列详情
    * @param
    * @return
    */
    @Operation(summary = "查询日志列详情")
    @GetMapping("v1/admin/logColumn/getById")
    @Actlog
    public LogColumnInfoResponse getById(@RequestParam Long logColumnId) {
        return logColumnBiz.getById(logColumnId);
    }

    /**
    * 删除
    * @param
    * @return
    */
    @Operation(summary = "删除")
    @DeleteMapping("v1/admin/logColumn/deleteById")
    @Actlog
    public void deleteByIds(@RequestParam List<Long> logColumnIds) {
        logColumnBiz.deleteByIds(logColumnIds);
    }


}