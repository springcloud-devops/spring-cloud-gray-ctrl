package serviceA.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import serviceA.interceptors.ParamInterceptor;

/**
 * Created by :Guozhihua
 * Date： 2017/8/21.
 */
@Configuration
public class MvcAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new ParamInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }



}
