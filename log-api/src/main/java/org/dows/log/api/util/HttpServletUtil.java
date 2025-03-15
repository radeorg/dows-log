package org.dows.log.api.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class HttpServletUtil {

    /**
     * 从请求中获取参数
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestParams(ServletRequest request) throws IOException {

        Map<String, Object> params = new HashMap<>();

        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap != null){
            parameterMap.forEach((key, values) -> params.put(key, values[0]));
        }
        String jsonBody = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8"));
        if(StrUtil.isNotBlank(jsonBody)){
            try{
                params.put("requestBody", JSONUtil.parseArray(jsonBody));
            }catch (Exception e){
                params.put("requestBody", JSONUtil.parseObj(jsonBody));
            }
        }
//        String contentType = request.getContentType();
        /*if ("application/x-www-form-urlencoded".equalsIgnoreCase(contentType) || "text/html".equalsIgnoreCase(contentType)) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            parameterMap.forEach((key, values) -> params.put(key, values[0]));
        }

        if ("application/json".equalsIgnoreCase(contentType)) {
            String jsonBody = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8"));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(jsonBody, Map.class);
            jsonMap.forEach((key, value) -> params.put(key, value.toString()));
        }*/
        return params;
        // 返回参数
       /* Map<String, String> params = new HashMap<>();
        // 获取内容格式
        String contentType = request.getContentType();
        if (contentType != null && !contentType.equals("")) {
            contentType = contentType.split(";")[0];
        }
        // form表单格式  表单形式可以从 ParameterMap中获取
        if ("appliction/x-www-form-urlencoded".equalsIgnoreCase(contentType)) {
            // 获取参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap != null) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    params.put(entry.getKey(), entry.getValue()[0]);
                }
            }
        }
        // json格式 json格式需要从request的输入流中解析获取
        if ("application/json".equalsIgnoreCase(contentType)) {
            // 使用IoUtil 类快速获取输入流内容
            String paramJson = null;
            try {
                paramJson = IoUtil.read(request.getInputStream(), Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                JSONObject entries = JSONUtil.parseObj(paramJson);
                params.putAll(entries.toBean(Map.class));
            } catch (Exception e) {
                JSONArray objects = JSONUtil.parseArray(paramJson);
                params.put("arrays",objects.toString());
            }

        }
        return params;*/
    }
}
