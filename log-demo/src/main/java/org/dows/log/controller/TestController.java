//package org.dows.log.controller;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.bean.copier.CopyOptions;
//import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.servlet.http.HttpServletRequest;
//import org.dows.log.entity.log.UserRequestLog;
//import org.dows.log.request.AddRequest;
//import org.dows.log.service.UserService;
//import org.dows.framework.crud.mybatis.utils.QueryWrapperUtils;
//import org.dows.log.api.tmp.DomainContextHolder;
//import org.dows.log.api.tmp.DomainMetadata;
//import org.dows.log.api.util.HttpServletUtil;
//import org.dows.log.api.annotation.DomainMapping;
//import org.dows.log.api.annotation.Actlog;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author: lujl
// * @Date: 2023\1\9 0009 14:19
// */
//@RestController
//public class TestController {
//    //    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private LogService service;
//
//    @Operation(summary = "testAuditLog")
//    @PostMapping(value = "/testAuditLog")
//    @Actlog(tables = {UserRequestLog.class, TestController.class})
//    public String testAuditLog(@RequestBody AddRequest addRequest) {
//        userService.insert(addRequest.getStr1(), addRequest.getStr2());
//        return "111";
////        return new ResponseEntity<>(Collections.EMPTY_MAP, HttpStatus.OK);
//    }
//
//    @Operation(summary = "testAuditLogDelete")
//    @Actlog(tables = {UserRequestLog.class, TestController.class})
//    @PostMapping(value = "/testAuditLogDelete")
//    public ResponseEntity<Object> testAuditLogDelete(@RequestBody AddRequest addRequest) {
//        Map<String, String> result = new HashMap<>(1);
//        userService.delete(11111);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @Operation(summary = "testAuditLogSelect")
//    @Actlog(tables = {UserRequestLog.class, TestController.class})
//    @PostMapping(value = "/testAuditLogSelect")
//    public ResponseEntity<Object> testAuditLogSelect(@RequestParam("name") String name) {
//        Map<String, String> result = new HashMap<>(1);
//        userService.queryAll(name);
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
