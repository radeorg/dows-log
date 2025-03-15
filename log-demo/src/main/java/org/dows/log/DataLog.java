package org.dows.log;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DataLog {
    private Map<String,Map<String, List<ColumnLog>>> databaseAndTable;
}
