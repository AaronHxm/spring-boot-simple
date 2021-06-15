package com.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName OpenAPIConfig.java
 * @Description TODO
 * @createTime 2021年05月27日 10:25:00
 */
@Configuration
@EnableSwagger2
public class OpenAPIConfig {
    /**
     * 前台API分组
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否开启swagger
               // .enable(true)
             //   .groupName("前端接口分组")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bokun.it.ctrl"))
                .paths(PathSelectors.any())
                //
                // 授权信息设置，必要的header token等认证信息
                //.securitySchemes(securitySchemes())

                // 授权信息全局应用
              //  .securityContexts(securityContexts())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件管理API")
                .description("博坤文件管理系统")
                //  .termsOfServiceUrl("http://www.qiwenshare.com:8762/")
                .contact(new Contact("huxianming", "", "huxianming@bokunit.com"))
                .version("1.0.0")
                .build();
    }
}
