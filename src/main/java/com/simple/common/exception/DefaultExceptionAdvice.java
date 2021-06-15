package com.simple.common.exception;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


import com.simple.common.model.RestResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import lombok.extern.slf4j.Slf4j;

/**
 * 异常通用处理
 *
 * @Author aaron.hu
 */
@RestControllerAdvice
@Slf4j
@ResponseBody
public class DefaultExceptionAdvice {
    /**
     * IllegalArgumentException异常处理返回json
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public RestResult handleIllegalArgumentException(IllegalArgumentException e) {
        return resultHandler("参数解析失败", e);
    }

//    @ExceptionHandler({RpcException.class})
//    public RestResult handleIRpcException(RpcException e) {
//        return resultHandler("rpc服务异常", e);
//    }

    /**
     * AccessDeniedException异常处理返回json
     */
    @ExceptionHandler({AccessDeniedException.class})
    public RestResult handleAccessDeniedException(AccessDeniedException e) {
        return resultHandler("没有权限请求当前方法", e);
    }

    /**
     * HttpRequestMethodNotSupportedException异常处理返回json
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return resultHandler("不支持当前请求方法", e);
    }

    /**
     * HttpMediaTypeNotSupportedException异常处理返回json
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public RestResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return resultHandler("不支持当前媒体类型", e);
    }

    /**
     * SQLException sql异常处理返回json
     */
    @ExceptionHandler({SQLException.class})
    public RestResult handleSQLException(SQLException e) {
        return resultHandler("服务运行SQLException异常", e);
    }

    /**
     * BadCredentialsException异常处理返回json
     */
//    @ExceptionHandler(BadCredentialsException.class)
//    public RestResult handleBadCredentialsException(BadCredentialsException e) {
//        return resultHandler("用户名或密码错误", e);
//    }

    /**
     * MissingServletRequestParameterException异常处理返回json
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return resultHandler(e.getMessage(), e);
    }

    /**
     * NullPointerException异常处理返回json
     */
    @ExceptionHandler(NullPointerException.class)
    public RestResult handleNullPointerException(NullPointerException e) {
        return resultHandler(e.getMessage(), e);
    }

    /**
     * MethodArgumentNotValidException异常处理返回json
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessage = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());
        return resultHandler(errorMessage.toString(), e);
    }

    /**
     * ParameterException异常统一处理返回json
     */
    @ExceptionHandler(ParameterException.class)
    public RestResult handleParameterException(ParameterException e) {
        return resultHandler(e);
    }

    /**
     * BusinessException异常统一处理返回json
     */
    @ExceptionHandler(BusinessException.class)
    public RestResult handleBusinessException(BusinessException e) {
        return resultHandler(e);
    }

    /**
     * RuntimeException异常统一处理返回json
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResult handleRuntimeException(RuntimeException e) {
        return resultHandler("未知异常", e);
    }

    /**
     * 所有异常统一处理返回json
     */
    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception e) {
        return resultHandler("未知异常", e);
    }

    private RestResult resultHandler(ApplicationException e) {
        log.error(e.getMessage(), e);
        return RestResult.failed(e.getCode(), e.getMessage(),e.getData());
    }

    private RestResult resultHandler(String msg, Exception e) {
        log.error(msg, e);
        return RestResult.failed(msg);
    }
}
