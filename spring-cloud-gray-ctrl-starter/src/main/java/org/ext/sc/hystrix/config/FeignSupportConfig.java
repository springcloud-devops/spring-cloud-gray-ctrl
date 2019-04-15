package org.ext.sc.hystrix.config;


import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class FeignSupportConfig {


    private  static Logger logger =LoggerFactory.getLogger(FeignSupportConfig.class);
    @Resource
    private Environment environment;

    @Bean
    @ConditionalOnClass(name={"org.springframework.cloud.openfeign.FeignClient"})
    public RequestInterceptor defaultInterceptor(){
      logger.info("============init feignClient intercept===========");
        return new FeignBaseRequestIntercept();
    }






}
