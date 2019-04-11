package api.hystrix;

import api.ServiceBApi;
import demo.sdk.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * Created by 23780 on 2018/3/22.
 */

@Component
public class ServiceBApiHystrix implements ServiceBApi {
    public BaseResponse sayHello(String name) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(500);
        baseResponse.setData("serviceB is down ");
        baseResponse.setStatus("down");
        return  baseResponse;
    }

    public BaseResponse sayLinkHello(String name) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(500);
        baseResponse.setData("serviceB is down ");
        baseResponse.setStatus("down");
        return  baseResponse;
    }
}
