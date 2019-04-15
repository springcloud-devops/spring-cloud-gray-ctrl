package api.hystrix;

import demo.sdk.BaseResponse;

public class CommonHystrix {
    protected BaseResponse getBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(500);
        baseResponse.setData("service is down ");
        baseResponse.setStatus("down");
        return baseResponse;
    }
}
