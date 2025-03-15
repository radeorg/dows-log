//package org.dows.log.rest.test;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.bean.copier.CopyOptions;
//import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.servlet.http.HttpServletRequest;
//import org.dows.framework.crud.mybatis.utils.QueryWrapperUtils;
//import org.dows.log.api.DomainContextHolder;
//import org.dows.log.api.DomainMetadata;
//import org.dows.log.api.annotation.Actlog;
//import org.dows.log.api.annotation.DomainMapping;
//import org.dows.log.api.util.HttpServletUtil;
//import org.dows.log.mysql.entity.LogActionEntity;
//import org.dows.log.mysql.entity.LogColumnEntity;
//import org.dows.log.mysql.entity.LogSettingEntity;
//import org.dows.log.mysql.entity.LogTableEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class TestController {
//    //    @Autowired
//
////    @Autowired
////    private LogService service;
//
//    @Operation(summary = "testAuditLog")
//    @PostMapping(value = "/testAuditLog")
//    @Actlog(tables = {LogActionEntity.class, LogColumnEntity.class})
//    public String testAuditLog(String id) {
//        return "111";
////        return new ResponseEntity<>(Collections.EMPTY_MAP, HttpStatus.OK);
//    }
//
//    @Operation(summary = "testAuditLogDelete")
//    @Actlog(tables = {LogSettingEntity.class, LogTableEntity.class})
//    @PostMapping(value = "/testAuditLogDelete")
//    public ResponseEntity<Object> testAuditLogDelete() {
//        Map<String, String> result = new HashMap<>(1);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @Operation(summary = "testAuditLogSelect")
//    @Actlog(tables = {LogColumnEntity.class})
//    @PostMapping(value = "/testAuditLogSelect")
//    public ResponseEntity<Object> testAuditLogSelect(@RequestParam("name") String name) {
//        Map<String, String> result = new HashMap<>(1);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @DomainMapping
//    @GetMapping("v1/{domain}/page")
//    public List<JSONObject> page(@PathVariable(value = "domain") String domain, HttpServletRequest httpServletRequest) {
//        Map<String, String> params = HttpServletUtil.getRequestParams(httpServletRequest);
//        final DomainMetadata domainMetadata = DomainContextHolder.get(domain);
//        final Object qo = BeanUtil.mapToBean(params, domainMetadata.getClazz(), true, CopyOptions.create());
//        final QueryWrapper<Object> predicate = QueryWrapperUtils.getPredicate(qo);
//        predicate.last("limit 1");
//        //final List<JSONObject> jsonObjects = service.selectList(domain, predicate);
//        //return jsonObjects;
//        return null;
//    }
//
//}
