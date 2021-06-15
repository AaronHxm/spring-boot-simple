package com.simple.log.aspect;

import cn.hutool.json.JSONUtil;
import com.simple.util.StringUtils;
import com.google.common.base.Stopwatch;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName LogAspect.java
 * @Description TODO
 * @createTime 2021年06月02日 15:47:00
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    private static final String MSG_PATTERN = "{}|{}|{}";
    @Autowired
    HttpServletRequest request;
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Stopwatch stopwatch = Stopwatch.createStarted();
        result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        ApiOperation apiAnno = method.getAnnotation(ApiOperation.class);
        String browser = StringUtils.getBrowser(request);
        String ip = StringUtils.getIp(request);
        String value = apiAnno.value();
        String cityInfo = StringUtils.getCityInfo(ip);
        String parameter = getParameter(method, joinPoint.getArgs());
        System.out.println(browser);
        System.out.println(ip);
        System.out.println(value);
        System.out.println(cityInfo);
        System.out.println(methodName);
        System.out.println(parameter);

        log.info(MSG_PATTERN,browser, ip , cityInfo, methodName);
        long elapsed = stopwatch.elapsed(TimeUnit.NANOSECONDS);

        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {

    }

    public String getUsername() {
//        try {
//            return SecurityUtils.getCurrentUsername();
//        }catch (Exception e){
//            return "";
//        }
        return "";
    }
    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }

    public static void main(String[] args) {


    }
}
