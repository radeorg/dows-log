package org.dows.log.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BinLogPageData {
    private List<Map<String,Object>> after;
    private Integer total;

}
