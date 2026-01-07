package com.hjy.config;

import com.hjy.utils.AliyunOSSOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean
    public AliyunOSSOperator aliyunOSSOperator(){
        return new AliyunOSSOperator();
    };
    // 要是
}
