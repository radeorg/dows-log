package org.dows.log.model;

import lombok.Data;

import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/18/2024 2:38 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Data
public class Database {
    private String name;
    private String host;
    private String port;
    private String user;
    private String password;
    private List<Table> tables;
}

