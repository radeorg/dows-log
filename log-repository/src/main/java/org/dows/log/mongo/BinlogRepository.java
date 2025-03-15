package org.dows.log.mongo;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.api.model.PageRequest;
import org.dows.framework.crud.api.model.PageResponse;
import org.dows.log.api.admin.request.ListBinLogRequest;
import org.dows.log.model.CellData;
import org.dows.log.model.RowData;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 4/20/2024 2:11 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class BinlogRepository {
    private final MongoTemplate mongoTemplate;

    public void save(Object data, String collectionName) {
        mongoTemplate.insert(data, collectionName);
    }

    public void addItemsToDocument(String key, Map<String, Object> after, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(key));
        Update update = new Update();
        update.addToSet("after", after);
        mongoTemplate.updateFirst(query, update, CellData.class, collectionName);
    }


    // 查询
    public RowData find(String key, String value, String collectionName) {
        // 查询最新一条数据
        return mongoTemplate.findOne(new Query(Criteria.where(key).is(value)), RowData.class, collectionName);
    }

    public PageResponse<RowData> findPaginatedDocuments(PageRequest<ListBinLogRequest> listBinLogRequest) {
        ListBinLogRequest queryObject = listBinLogRequest.getQueryObject();
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(queryObject.getKey()) && StrUtil.isNotEmpty(queryObject.getValue())) {
            criteria = Criteria.where(queryObject.getKey()).is(queryObject.getValue());
        }
        Query queryPage = new Query(criteria);
        long total = mongoTemplate.count(queryPage, RowData.class, queryObject.getCollectionName());

        //分页
        Long pageNo = listBinLogRequest.getPageNo();
        Long pageSize = listBinLogRequest.getPageSize();
        queryPage.skip((pageNo - 1) * pageSize).limit(pageSize.intValue());
        List<RowData> rowData = mongoTemplate.find(queryPage, RowData.class, queryObject.getCollectionName());
        return new PageResponse(total, pageNo, pageSize, pageNo, rowData);
    }

}

