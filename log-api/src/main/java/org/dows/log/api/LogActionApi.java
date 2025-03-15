package org.dows.log.api;

import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListRequestLogRequest;
import org.dows.log.api.admin.request.SaveLogActionRequest;
import org.dows.log.api.admin.request.RequestLog;

import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/22/2024 5:18 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */

public interface LogActionApi {

    void save(List<SaveLogActionRequest> logActionRequests);

    Boolean logEnable(String signatrue);

    PageResponse<RequestLog> requestLogPage(PageRequest<ListRequestLogRequest> listBinLogRequest);

}
