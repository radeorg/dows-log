package org.dows.log.api.admin.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
* @description 
*
* @author 
* @date 
*/
@Data
@NoArgsConstructor
@Schema(name = "ListBinlog 对象", title = "ListBinlog列表")
public class ListBinlogResponse {
    @Schema(name = "ID", title = "ID")
    private String id;
    @Schema(name = "变化后数据", title = "变化后数据")
    private List<Map<String,Object>> after;


}
