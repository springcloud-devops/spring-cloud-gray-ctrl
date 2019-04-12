package api.hystrix;

import api.ServiceBApi;
import demo.sdk.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * Created by 23780 on 2018/3/22.
 */

@Component
public class ServiceBApiHystrix implements ServiceBApi {
    @Override
    public BaseResponse sayHello(String name) {
        return getBaseResponse();
    }

    @Override
    public BaseResponse sayLinkHello(String name) {
        return getBaseResponse();
    }

    private BaseResponse getBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(500);
        baseResponse.setData("serviceB is down ");
        baseResponse.setStatus("down");
        return baseResponse;
    }
}
