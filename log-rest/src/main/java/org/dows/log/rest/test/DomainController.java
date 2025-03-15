//package org.dows.log.rest.test;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.bean.copier.CopyOptions;
//import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import jakarta.servlet.http.HttpServletRequest;
//import org.dows.framework.api.Response;
//import org.dows.log.api.DomainContextHolder;
//import org.dows.log.api.DomainMetadata;
//import org.dows.log.api.HttpServletUtil;
//import org.dows.log.api.annotation.DomainMapping;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//public class DomainController {
//
//    protected LogService service;
//
//    /**
//     * 获取所有的domains
//     *
//     * @return
//     */
//    @DomainMapping
//    @GetMapping(value = "v1/domains")
//    public Response getDomains() {
//        return null;
//    }
//
//    @DomainMapping
//    @GetMapping(value = "v1/{domain}/{id}")
//    public Response getById(@PathVariable(value = "domain") String domain, @PathVariable("id") Long id) {
//        service.selectById(domain, id);
//        return null;
//    }
//
//    @DomainMapping
//    @GetMapping(value = "v1/{domain}/{ids}")
//    public Response getByIds(@PathVariable(value = "domain") String domain, @PathVariable("ids") String ids) {
//        service.selectByIds(domain, ids);
//        return null;
//    }
//
//    @DomainMapping
//    @DeleteMapping("v1/{domain}/{ids}")
//    public Response remove(@PathVariable Integer[] ids) {
//        return null;
//    }
//
//    @DomainMapping
//    @GetMapping("v1/{domain}/list")
//    public Response list(@PathVariable(value = "domain") String domain, HttpServletRequest httpServletRequest) {
//        service.selectListByMap(null, null);
//        return null;
//    }
//
//    @DomainMapping
//    @GetMapping("v1/{domain}/page")
//    public Response page(@PathVariable(value = "domain") String domain, HttpServletRequest httpServletRequest) {
//        Map<String, String> params = HttpServletUtil.getRequestParams(httpServletRequest);
//        final DomainMetadata domainMetadata = DomainContextHolder.get(domain);
//        final Map<String, Object> fieldValues = domainMetadata.getFieldValues();
//        /*for (String field : domainMetadata.getFields()) {
//            final String value = params.get(field);
//            final Object o = fieldValues.get(field);
//            if(o!= null){
//                fieldValues.put(field,value);
//            }
//        }*/
//        final Object qo = BeanUtil.mapToBean(params, domainMetadata.getClazz(), true, CopyOptions.create());
//
//        final QueryWrapper<Object> predicate = QueryWrapperUtils.getPredicate(qo);
//        final List<JSONObject> jsonObjects = service.selectList(domain, predicate);
//        return null;
//    }
//
//    @DomainMapping
//    @PostMapping("v1/{domain}")
//    public Response add(@RequestBody Map<String, String> data) {
//
//        return null;
//    }
//
//    @DomainMapping
//    @PutMapping("v1/{domain}")
//    public Response edit(@RequestBody Map<String, String> data) {
//        return null;
//    }
//
//
//}
