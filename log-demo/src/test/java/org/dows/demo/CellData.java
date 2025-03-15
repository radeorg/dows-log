package org.dows.demo;

import lombok.Data;

@Data
public class CellData {
    private String column;
    private String before;
    private String after;
    private String account;
    private String timestamp;
}