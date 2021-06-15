package com.simple.common.annotation;

import java.lang.annotation.*;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName ResponseResult.java
 * @Description Controller添加该注解，则response包装成统一的json格式
 * @createTime 2021年05月27日 11:14:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
