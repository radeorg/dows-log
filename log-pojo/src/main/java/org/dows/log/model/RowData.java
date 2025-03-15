package org.dows.log.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/18/2024 5:52 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Builder
@Data
@Schema(name = "行数据对象", title = "行数据对象")
public class RowData {

    @Schema(name = "ID", title = "ID")
    private String id;
    // timestamp
//    private String key;
    @Schema(name = "修改后数据", title = "修改后数据")
    private List<Map<String, Object>> after;

    // 总条数
    @Schema(name = "总条数", title = "总条数")
    private Integer total;
//    private List<CellData> cells;
}

