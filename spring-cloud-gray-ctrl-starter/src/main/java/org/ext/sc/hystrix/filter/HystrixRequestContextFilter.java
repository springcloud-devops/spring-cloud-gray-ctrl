package org.ext.sc.hystrix.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.ext.sc.hystrix.utils.RequestContextHelper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.ApplicationContext;

public class HystrixRequestContextFilter extends ZuulFilter  {
    private ApplicationContext context;

    public HystrixRequestContextFilter(ApplicationContext context) {
        this.context = context;
        registerHystrixRequestContextPostFilter();
    }

    private void registerHystrixRequestContextPostFilter() {
        BeanDefinition beanDefinition =
            new RootBeanDefinition(HystrixRequestContextPostFilter.class);
        beanDefinition.setScope("singleton");
        BeanDefinitionHolder beanDefinitionHolder =
            new BeanDefinitionHolder(beanDefinition , "hystrixRequestContextPostFilter" );
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder , (BeanDefinitionRegistry) this.context);
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HystrixRequestContext.initializeContext();
        RequestContextHelper.set(RequestContext.getCurrentContext());
        System.out.println("set Thread is : "+Thread.currentThread().getName());
        return null;
    }

    private static class HystrixRequestContextPostFilter extends ZuulFilter {
        @Override
        public String filterType() {
            return FilterConstants.POST_TYPE;
        }

        @Override
        public int filterOrder() {
            return -100;
        }

        @Override
        public boolean shouldFilter() {
            return true;
        }

        @Override
        public Object run() throws ZuulException {
            HystrixRequestContext context = HystrixRequestContext.getContextForCurrentThread();
            if (HystrixRequestContext.isCurrentThreadInitialized()) {
                System.out.println("===========hystrix  shutdown==========");
                context.shutdown();
            }
            return null;
        }
    }
}
