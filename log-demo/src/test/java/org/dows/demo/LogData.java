package org.dows.demo;

import lombok.Data;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/18/2024 5:50 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Data
public class LogData {
    private String id;
    // database:table
    private String key;
    private RowData rowData;


}

