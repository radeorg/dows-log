package org.dows.log.mysql.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/22/2024 5:13 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "LogAction", title = "日志操作")
@TableName("log_action")
public class LogActionEntity {

    @TableId(type = IdType.ASSIGN_ID)
    //请求日志ID
    private Long logActionId;
    //请求uri
    private String uri;
    //请求方法[方法签名]
    private String action;
    //请求方法[get|post|update|delete]
    private String method;
    //方法描述
    private String descr;

    private String signature;

    //关联是表名[类全面]
    private String tables;
    //是否开启
    private Boolean enable;
    //数据版本号
    @Schema(name = "ver", title = "数据版本号")
    private Integer ver;
    //是否逻辑删
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(name = "deleted", title = "是否逻辑删")
    private Boolean deleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(name = "dt", title = "")
    private Date dt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(name = "account_id", title = "账号ID")
    private Long accountId;
}

