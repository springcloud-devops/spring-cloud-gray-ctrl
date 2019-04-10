package serviceA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by :Guozhihua
 * Date： 2017/11/15.
 */
@Configuration
@EnableSwagger2
//激活bean的允许环境
@Profile(value = "default")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).host("127.0.0.1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("sc.demo.serviceA.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSO 用户统一中心接口文档")
                .description("逐步完善中 ")
                .termsOfServiceUrl("http://xxx.sdfsd.com")
                .version("1.0")
                .build();
    }


}
