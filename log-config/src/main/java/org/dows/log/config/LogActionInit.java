package org.dows.log.config;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.log.api.LogActionApi;
import org.dows.log.api.admin.request.SaveLogActionRequest;
import org.dows.log.api.annotation.Actlog;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogActionInit {
    // todo 待实现
    private final LogActionApi logActionApi;
    private final LogProperties logProperties;

    /**
     * 扫描并返回所有需要权限处理的接口资源
     * 这里模拟扫描，借助 org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping
     */

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @PostConstruct
    public void init() {
        Boolean actLogScan = logProperties.isActLogScan();
        log.info("重新扫描日志接口信息是否开启:{}", actLogScan);
        if(actLogScan){
            log.info("开始扫描日志接口信息");
            List<SaveLogActionRequest> logActionRequests = new ArrayList<>();
            // 拿到所有接口信息，并开始遍历
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            handlerMethods.forEach((info, handlerMethod) -> {
                String appId = "";
                // 拿到类(模块)上的权限注解（可填可不填）
                Class<?> beanType = handlerMethod.getBeanType();

                // 拿到接口方法上的权限注解
                Method method = handlerMethod.getMethod();
                Actlog actlog = method.getAnnotation(Actlog.class);
                Operation operation = method.getAnnotation(Operation.class);
                String name;
                // package.class.method or classAuth.code.methodAuth.code
                if (operation != null) {
                    name = operation.summary();
                } else {
                    name = method.getName();
                }

                // 拿到该接口方法的请求方式(GET、POST等)
                Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
                // 如果一个接口方法标记了多个请求方式，权限id是无法识别的，不进行处理
                if (methods.size() != 1) {
                    return;
                }
                // 将请求方式和路径用`:`拼接起来，以区分接口。比如：GET:/user/{id}、POST:/user/{id}
                String httpMethod = methods.toArray()[0].toString();
                String path = Objects.requireNonNull(info.getPathPatternsCondition()).getPatterns().toArray()[0] + "";
                // 日志设置
                if (actlog != null) {
                    String action = beanType.getPackageName() + "." + method.getName();
                    String tables = String.join(",", Arrays.stream(actlog.tables()).map(Class::getName).toList());
                    SaveLogActionRequest logActionRequest = SaveLogActionRequest.builder()
                            .uri(path)
                            .method(httpMethod)
                            .appId(appId)
                            .descr(name)
                            .signature(method.toString())
                            .action(action)
                            .tables(tables)
                            .enable(actlog.enable())
                            .build();
                    logActionRequests.add(logActionRequest);
                }
            });
            // todo save logActionRequests
            logActionApi.save(logActionRequests);
            log.info("日志扫描初始化完成");
        }
    }
}
