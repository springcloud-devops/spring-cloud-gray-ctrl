package org.ext.sc.hystrix.utils;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import com.netflix.zuul.context.RequestContext;

public class RequestContextHelper {

    public final static HystrixRequestVariableDefault<RequestContext> HYSTRIX_REQUEST_VARIABLE = new HystrixRequestVariableDefault();

    public static void set(RequestContext context) {

        HYSTRIX_REQUEST_VARIABLE.set(context);
    }

    public static void remove() {
        HYSTRIX_REQUEST_VARIABLE.remove();
    }

    public static RequestContext get() {
        RequestContext context = HYSTRIX_REQUEST_VARIABLE.get();
        return context;
    }


}
