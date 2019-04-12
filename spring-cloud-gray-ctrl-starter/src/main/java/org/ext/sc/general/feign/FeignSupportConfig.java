package org.ext.sc.general.feign;


import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportConfig {

    private static Logger logger = LoggerFactory.getLogger(FeignSupportConfig.class);
    @Bean
    public RequestInterceptor requestInterceptor(){
        logger.info("注入bean :{}",this.getClass());
        return new FeignBaseRequestIntercept();
    }

}
