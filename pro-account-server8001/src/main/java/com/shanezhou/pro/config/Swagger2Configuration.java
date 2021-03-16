package com.shanezhou.pro.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ShaneZhou
 * @since 2020/12/4 周五 14:29:12
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(value = {BeanValidatorPluginsConfiguration.class})
public class Swagger2Configuration {

    @Value("${swagger2.switch}")
    public boolean swaggerSwitch;

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerSwitch)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试标题")
                .description("测试描述")
                .version("测试版本")
                .license("测试license")
                .licenseUrl("测试licenseUrl")
                .contact(new Contact("SHANE", "https://book.douban.com/", "x123@123.com"))
                .termsOfServiceUrl("https://www.baidu.com")
                .build();
    }
}
