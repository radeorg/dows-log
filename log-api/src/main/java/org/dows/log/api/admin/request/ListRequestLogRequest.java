package org.dows.log.api.admin.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "ListBinLog 对象", title = "ListBinLog查询入参")
public class ListRequestLogRequest {

    @JsonIgnore
    @Schema(title = "键")
    private String key;

    @JsonIgnore
    @Schema(title = "值")
    private String value;

    @JsonIgnore
    @Schema(title = "集合名称")
    private String collectionName;


    @Schema(title = "日志类型")
    private String logType;

    @Schema(title = "请求方法")
    private String method;

    @Schema(title = "请求IP")
    private String requestIp;
//    @Schema(title = "时间间隔")
//    private String time;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "地址")
    private String address;
    @Schema(title = "浏览器")
    private String browser;
    @Schema(title = "应用Id")
    private String appId;
}
