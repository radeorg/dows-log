package org.dows.log.handler;

import lombok.RequiredArgsConstructor;
import org.dows.app.api.AppApi;
import org.dows.app.api.admin.response.AppInstanceResponse;
import org.dows.log.config.LogConfig;
import org.dows.log.api.admin.request.RequestLog;
import org.dows.log.mongo.ActlogRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LogActlogHandler {

    private final LogConfig logConfig;
    private final AppApi appApi;


    private final ActlogRepository actlogRepository;
    public void save(RequestLog requestLog){
        actlogRepository.save(requestLog,getCollectionName(requestLog.getAppId()));
    }

    public String getCollectionName(String appId){
        AppInstanceResponse appInstance = appApi.getAppInstance(appId);
        String collectionName = null;
        if(null == appInstance){
            collectionName = logConfig.getActlogCollectionName();
        }else{
            collectionName = String.format("shdy:%s:actlog", appInstance.getAppCode());
        }
        return collectionName;
    }
}
