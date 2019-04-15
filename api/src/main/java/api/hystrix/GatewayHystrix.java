package api.hystrix;

import api.GatewayApi;
import demo.sdk.BaseResponse;
import org.springframework.stereotype.Component;

@Component
public class GatewayHystrix  extends CommonHystrix implements GatewayApi {
    @Override
    public BaseResponse sayHelloB(String name) {
        return super.getBaseResponse();
    }

    @Override
    public BaseResponse sayLinkHelloB(String name) {
        return super.getBaseResponse();
    }

    @Override
    public BaseResponse sayLinkHelloC(String name) {
        return super.getBaseResponse();
    }
}
