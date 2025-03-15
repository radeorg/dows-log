package org.dows.log.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CellData {
    private String column;
//    private Object before;
    private Object after;
    private String account;
    private Date timestamp;
}