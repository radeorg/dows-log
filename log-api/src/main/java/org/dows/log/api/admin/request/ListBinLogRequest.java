package org.dows.log.api.admin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "ListBinLog 对象", title = "ListBinLog查询入参")
public class ListBinLogRequest {

    @Schema(title = "key")
    private String key;

    @Schema(title = "value")
    private String value;

    @Schema(title = "collectionName")
    private String collectionName;
}
