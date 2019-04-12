package org.ext.sc.hystrix.config;

import com.netflix.zuul.ZuulFilter;
import org.ext.sc.hystrix.filter.HystrixRequestContextFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {
    @Bean
    public ZuulFilter hystrixRequestContextFilter(ApplicationContext context) {
        return new HystrixRequestContextFilter(context);
    }
}
