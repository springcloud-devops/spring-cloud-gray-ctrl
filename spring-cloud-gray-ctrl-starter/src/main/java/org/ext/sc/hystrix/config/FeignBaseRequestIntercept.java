package org.ext.sc.hystrix.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertTrue;
import java.util.Enumeration;

public class FeignBaseRequestIntercept implements RequestInterceptor {

    private static Logger logger = LoggerFactory.getLogger(FeignBaseRequestIntercept.class);


    @Override
    public void apply(RequestTemplate requestTemplate) {

        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }
            } else {
                logger.warn("attributes is null ,请检查feign的配置和灰度规则的开关是是否正确");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}