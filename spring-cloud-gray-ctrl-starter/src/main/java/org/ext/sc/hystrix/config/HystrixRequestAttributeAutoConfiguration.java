package org.ext.sc.hystrix.config;

import com.netflix.hystrix.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ Hystrix.class })
//@ConditionalOnProperty(value = "hystrix.propagate.request-attribute.enabled", matchIfMissing = false,havingValue = "true")
@EnableConfigurationProperties(HystrixRequestAttributeProperties.class)
public class HystrixRequestAttributeAutoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(HystrixRequestAttributeAutoConfiguration.class);
    @Bean
    public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration() {
        logger.info("init config HystrixRequestAttributeAutoConfiguration beans...");
        return new RequestAttributeHystrixConcurrencyStrategy();
    }


}
