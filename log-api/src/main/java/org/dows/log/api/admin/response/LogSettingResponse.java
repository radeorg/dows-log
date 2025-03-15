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
@Schema(name = "LogSetting 对象", title = "日志配置信息")
public class LogSettingResponse{
    @Schema(title = "日志配置ID")
    private Long logSettingId;

    @Schema(title = "应用ID")
    private String appId;

    @Schema(title = "数据库")
    private String databaseName;

    @Schema(title = "数据库配置")
    private String databaseJson;


}
