package org.dows.log.mongo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListRequestLogRequest;
import org.dows.log.api.admin.request.RequestLog;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/20/2024 2:10 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class ActlogRepository/* implements MongoRepository<RequestLog, String> */ {
    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;

    public void save(RequestLog data, String collectionName) {
       /* String dd = "{\"ddd\":{\"dd\":\"cc\",\"cc\":\"vv\"}}";
        try {
            data = objectMapper.readValue(dd, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/
        mongoTemplate.insert(data, collectionName);
    }

    public PageResponse<RequestLog> getByCondition(PageRequest<ListRequestLogRequest> listActLogRequest){
        ListRequestLogRequest queryObject = listActLogRequest.getQueryObject();
        List<Criteria> criteriaChain = new ArrayList<>();
        if (StrUtil.isNotEmpty(queryObject.getLogType()) ) {
            Criteria criteria = Criteria.where("logType").is(queryObject.getLogType());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getMethod()) ) {
            Criteria criteria = Criteria.where("method").regex(queryObject.getMethod());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getRequestIp()) ) {
            Criteria criteria = Criteria.where("requestIp").regex(queryObject.getRequestIp());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getUsername()) ) {
            Criteria criteria = Criteria.where("username").regex(queryObject.getUsername());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getAddress()) ) {
            Criteria criteria = Criteria.where("address").regex(queryObject.getAddress());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getBrowser()) ) {
            Criteria criteria = Criteria.where("browser").regex(queryObject.getBrowser());
            criteriaChain.add(criteria);
        }
        if (StrUtil.isNotEmpty(queryObject.getAppId()) ) {
            Criteria criteria = Criteria.where("appId").is(queryObject.getAppId());
            criteriaChain.add(criteria);
        }
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaChain);
        Query queryPage = new Query(criteria);
        long total = mongoTemplate.count(queryPage, RequestLog.class, queryObject.getCollectionName());

        //分页
        Long pageNo = listActLogRequest.getPageNo();
        Long pageSize = listActLogRequest.getPageSize();
        queryPage.skip((pageNo - 1) * pageSize).limit(pageSize.intValue());
        List<RequestLog> requestLogs = mongoTemplate.find(queryPage, RequestLog.class, queryObject.getCollectionName());
        return new PageResponse(total, pageNo, pageSize, pageNo, requestLogs);

    }


}

