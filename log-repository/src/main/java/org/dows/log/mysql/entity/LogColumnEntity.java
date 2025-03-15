package org.dows.log.mysql.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 日志列(LogColumn)实体类
 *
 * @author lait
 * @since 2024-04-20 10:54:45
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "LogColumn", title = "日志列")
@TableName("log_column")
public class LogColumnEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(name = "logColumnId", title = "日志列ID")
    private Long logColumnId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(name = "logTableId", title = "日志表ID")
    private Long logTableId;

    @Schema(name = "appId", title = "应用ID")
    private String appId;

    @Schema(name = "columnJson", title = "列JSON")
    private String columnJson;

    @Schema(name = "ver", title = "数据版本号")
    private Integer ver;

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

