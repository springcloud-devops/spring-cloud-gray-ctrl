package serviceA.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.ext.sc.hystrix","api"})
public class GrayConfig {
}
