package com.simple.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.simple.common.Interceptor.ResponseResultInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.math.BigInteger;
import java.util.List;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName DefaultWebMvcConfig.java
 * @Description TODO
 * @createTime 2021年05月27日 13:26:00
 */
public class DefaultWebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * Token参数解析
     *
     * @param argumentResolvers
     *            解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注入应用信息
     //   argumentResolvers.add(new ClientArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 统一返回格式处理
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**/login/**")
                .excludePathPatterns("/actuator/health/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/v2/api-docs-ext/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/doc.html");
    }

    /**
     * 设置资源文件目录
     *
     * @param registry
     */
    /**
     * 设置资源文件目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
     //   super.addResourceHandlers(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.DisableCircularReferenceDetect);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }


}
