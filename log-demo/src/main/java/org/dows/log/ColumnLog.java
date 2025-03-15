package org.dows.log;

import lombok.Data;

import java.util.Date;

@Data
public class ColumnLog {
    private String column;
    private String before;
    private String after;
    private String account;
    private Date timestamp;
}
