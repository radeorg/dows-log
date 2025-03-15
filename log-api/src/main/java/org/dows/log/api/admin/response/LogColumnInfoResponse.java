package org.dows.log.api.admin.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* @description 
*
* @author 
* @date 
*/
@Data
@NoArgsConstructor
@Schema(name = "LogColumnInfo 对象", title = "日志列信息")
public class LogColumnInfoResponse{
    @Schema(title = "日志列ID")
    private Long logColumnId;

    @Schema(title = "日志表ID")
    private Long logTableId;

    @Schema(title = "应用ID")
    private String appId;

    @Schema(title = "列JSON")
    private String columnJson;


}
