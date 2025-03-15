package org.dows.log.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.annotation.Namespace;
import org.dows.log.api.admin.request.ListLogSettingRequest;
import org.dows.log.api.admin.request.SaveLogSettingRequest;
import org.dows.log.api.admin.response.ListLogSettingResponse;
import org.dows.log.api.admin.response.LogSettingResponse;
import org.dows.log.api.annotation.Actlog;
import org.dows.log.biz.admin.LogSettingBiz;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @description project descr:应用api:日志配置
*
* @author lait.zhang
* @date 2024年4月20日 上午10:44:44
*/
@Namespace(module = "log", name = "日志配置", code = "log.logSetting", path = "/")
@RequiredArgsConstructor
@RestController
@Tag(name = "日志配置", description = "日志配置")
public class LogSettingRest {
    private final LogSettingBiz logSettingBiz;

    /**
    * 创建或更新日志配置
    * @param
    * @return
    */
    @Operation(summary = "创建或更新日志配置")
    @PostMapping("v1/admin/logSetting/save")
    @Actlog
    public void save(@RequestBody @Validated List<SaveLogSettingRequest> saveLogSetting ) {
        logSettingBiz.save(saveLogSetting);
    }

    /**
    * 查询日志配置列表
    * @param
    * @return
    */
    @Operation(summary = "查询日志配置列表")
    @PostMapping("v1/admin/logSetting/list")
    @Actlog
    public List<ListLogSettingResponse> list(@RequestBody @Validated ListLogSettingRequest listLogSetting) {
        return logSettingBiz.list(listLogSetting);
    }

    /**
    * 分页查询日志配置列表
    * @param
    * @return
    */
    @Operation(summary = "分页查询日志配置列表")
    @PostMapping("v1/admin/logSetting/page")
    @Actlog
    public ListLogSettingResponse page(@RequestBody @Validated ListLogSettingRequest listLogSetting) {
        return logSettingBiz.page(listLogSetting);
    }

    /**
    * 查询日志配置详情
    * @param
    * @return
    */
    @Operation(summary = "查询日志配置详情")
    @GetMapping("v1/admin/logSetting/getById")
    @Actlog
    public LogSettingResponse getById(@RequestParam Long logSettingId) {
        return logSettingBiz.getById(logSettingId);
    }

    /**
    * 删除
    * @param
    * @return
    */
    @Operation(summary = "删除")
    @DeleteMapping("v1/admin/logSetting/deleteById")
    @Actlog
    public void deleteByIds(@RequestParam List<Long> logSettingIds) {
        logSettingBiz.deleteByIds(logSettingIds);
    }


}