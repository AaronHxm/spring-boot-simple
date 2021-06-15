package com.simple.common.Interceptor;

import com.alibaba.fastjson.JSON;
import com.simple.common.annotation.ResponseResult;
import com.simple.common.model.RestResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName ResponseResultHandler.java
 * @Description TODO
 * @createTime 2021年05月27日 11:12:00
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice {
    // 标记名称
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    // 请求是包含了包装注解标记，没有则直接返回，不需要重写返回体
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求是否有包装标记
        ResponseResult responseResult = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof RestResult) {
            return (RestResult) body;
        }
        if (body instanceof String) {
            String str = JSON.toJSONString(RestResult.success(body));
            return str;
        }
        return RestResult.success(body);
    }
}
