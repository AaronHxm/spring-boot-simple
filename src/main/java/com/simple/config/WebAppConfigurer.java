package com.simple.config;

import com.simple.common.Interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName WebAppConfigurer.java
 * @Description TODO
 * @createTime 2021年05月27日 11:16:00
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Value("${bokun.file.location}")
    private String location;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/
    @Value("${bokun.file.resourceHandler}")
    private String resourceHandler;//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**

    @Bean
    public ResponseResultInterceptor getMyInterceptor() {
        return new ResponseResultInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/error")
                .excludePathPatterns(resourceHandler)
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/docs.html")
                .addPathPatterns("/**");


    }

    /**
     * 配置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径
        //映射本地文件时，开头必须是 file:/// 开头，表示协议
        registry.addResourceHandler("docs.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler(resourceHandler).addResourceLocations("file:///" + location);
    }
}
