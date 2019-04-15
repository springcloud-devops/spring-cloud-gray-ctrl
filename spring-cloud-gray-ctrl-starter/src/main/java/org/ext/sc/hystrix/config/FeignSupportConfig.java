package org.ext.sc.hystrix.config;


import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.annotation.Resource;

@Configuration
public class FeignSupportConfig {


    private  static Logger logger =LoggerFactory.getLogger(FeignSupportConfig.class);
    @Resource
    private Environment environment;


    @Bean
    public RequestInterceptor defaultInterceptor(){
      logger.info("============init feignClient===========");
        return new FeignBaseRequestIntercept();
    }






}
