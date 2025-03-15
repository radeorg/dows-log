package org.dows.log;

import lombok.Data;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/18/2024 2:39 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Data
public class Column {
    private String name;
    private String type;
    private String comment;
    private Boolean primaryKey;
    private Boolean autoIncrement;
    private boolean selected;
}

