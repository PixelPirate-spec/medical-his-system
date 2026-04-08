package com.hospital.medicalhisbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI medicalHisOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("医疗 HIS 系统 API 文档")
                        .version("1.0")
                        .description("基于 Spring Boot 3.2.4 + SpringDoc 的医疗 HIS 系统接口文档"));
    }
}