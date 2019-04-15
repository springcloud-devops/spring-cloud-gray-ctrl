package api.hystrix;

import api.ServiceBApi;
import demo.sdk.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * Created by 23780 on 2018/3/22.
 */

@Component
public class ServiceBApiHystrix extends CommonHystrix implements ServiceBApi {
    @Override
    public BaseResponse sayHello(String name) {
        return getBaseResponse();
    }

    @Override
    public BaseResponse sayLinkHello(String name) {
        return getBaseResponse();
    }

    @Override
    public BaseResponse sayLinkHelloDirc(String name) {
        return getBaseResponse();
    }


}
