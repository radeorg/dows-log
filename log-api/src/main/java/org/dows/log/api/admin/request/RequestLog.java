package org.dows.log.api.admin.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 请求日志
 */
@Builder
@Data
@Schema(name = "请求日志对象", title = "请求日志对象")
public class RequestLog {

    @Schema(name = "ID", title = "ID")
    private String id;

    @Schema(name = "描述", title = "描述")
    private String descr;

    @Schema(name = "日志类型", title = "日志类型")
    private String logType;

    @Schema(name = "请求方法", title = "请求方法")
    private String method;

    @Schema(name = "请求参数", title = "请求参数")
    private String params;

    @Schema(name = "请求IP", title = "请求IP")
    private String requestIp;

    @Schema(name = "请求时间", title = "请求时间")
    private Long time;

    @Schema(name = "用户名", title = "用户名")
    private String username;

    @Schema(name = "请求地址", title = "请求地址")
    private String address;

    @Schema(name = "浏览器", title = "浏览器")
    private String browser;

    @Schema(name = "操作系统", title = "操作系统")
    private String operateSystem;

    @Schema(name = "是否删除", title = "是否删除")
    private Boolean deleted;

    @Schema(name = "异常详情", title = "异常详情")
    private String exceptionDetail;

    @Schema(name = "应用ID", title = "应用ID")
    private String appId;

    @Schema(name = "账户ID", title = "账户ID")
    private Long accountId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "创建时间", title = "创建时间")
    private Date dt;

    @Schema(name = "创建时间", title = "创建时间")
    private String createTime;

    @Schema(name = "表名", title = "表名")
    private String tables;
    @Schema(name = "返回数据", title = "返回数据")
    private String response;

}