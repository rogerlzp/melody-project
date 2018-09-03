package com.melody.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * swagger配置
 * url: /swagger-ui.html
 * @author konghang
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "com.melody.swagger")
@Data
public class SwaggerConfig {

    private String basePackage;

    private String title;

    private String description;

    private String termsOfServiceUrl;

    private String contactName;

    private String contactUrl;

    private String contactMail;

    private String version;

    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                //controller路径
                .apis(RequestHandlerSelectors.basePackage(this.basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title(this.title)
                .description(this.description)
                .termsOfServiceUrl(this.termsOfServiceUrl)
                .contact(new Contact(this.contactName, this.contactUrl, this.contactMail))
                .version(this.version)
                .build();

    }

}
