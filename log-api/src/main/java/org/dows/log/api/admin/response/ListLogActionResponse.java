package org.dows.log.api.admin.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/22/2024 5:13 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Data
@NoArgsConstructor
@Schema(name = "操作日志返回对象", title = "操作日志返回对象")
public class ListLogActionResponse {
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
    //关联是表名[类全面]
    @Schema(name = "关联是表名[类全面]", title = "关联是表名[类全面]")
    private String tables;
    // 应用ID
    @Schema(name = "应用ID", title = "应用ID")
    private String appId;
    //是否开启
    @Schema(name = "是否开启", title = "是否开启")
    private Boolean enable;

}

