package serviceA.controller;

import api.GatewayApi;
import api.ServiceBApi;
import demo.sdk.BaseResponse;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by :Guozhihua
 * Date： 2017/8/17.
 */

@Api(value = "/xx", description = "测试接口API Controller", basePath = "http://www.neudev.cn/", position = 1)
@RestController
@RequestMapping("/xx")
public class ATestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("TestController");

    @Autowired
    private GatewayApi gatewayApi;


    @RequestMapping (value = "test/{userName}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse  test(@PathVariable("userName") String username) {
        BaseResponse baseResponse =gatewayApi.sayHelloB(username);
        return baseResponse;
    }

    @RequestMapping (value = "test_link/{userName}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse  test_link(@PathVariable("userName") String username) {
        BaseResponse baseResponse =gatewayApi.sayLinkHelloB(username);
        return baseResponse;
    }




}
