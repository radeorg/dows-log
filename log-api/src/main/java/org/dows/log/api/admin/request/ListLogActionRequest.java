package org.dows.log.api.admin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "操作日志列表对象", title = "操作日志列表对象")
public class ListLogActionRequest {
    //请求日志ID
    @Schema(name = "请求日志ID", title = "请求日志ID")
    private Long logActionId;
    //请求uri
    @Schema(name = "请求uri", title = "请求uri")
    private String uri;
    //请求方法[方法名]
    @Schema(name = "请求方法[方法名]", title = "请求方法[方法名]")
    private String action;
    //请求方法[get|post|update|delete]
    @Schema(name = "请求方法[get|post|update|delete]", title = "请求方法[get|post|update|delete]")
    private String method;
    //方法描述
    @Schema(name = "方法描述", title = "方法描述")
    private String descr;
    // 方法签名
    @Schema(name = "方法签名", title = "方法签名")
    private String signature;

}
