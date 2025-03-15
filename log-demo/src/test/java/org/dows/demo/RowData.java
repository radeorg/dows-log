package org.dows.demo;

import lombok.Data;

import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/18/2024 5:52 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Data
public class RowData {
    private String id;
    // timestamp
    private String key;
    private List<CellData> cells;
}

