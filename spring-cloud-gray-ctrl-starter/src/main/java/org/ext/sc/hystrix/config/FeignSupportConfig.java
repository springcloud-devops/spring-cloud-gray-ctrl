package org.ext.sc.hystrix.config;


import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportConfig {
    @Bean
    public RequestInterceptor defaultInterceptor(){
        return new FeignBaseRequestIntercept();
    }






}
