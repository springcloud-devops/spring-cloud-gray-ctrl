package serviceB.controller;

import api.GatewayApi;
import api.ServiceCApi;
import demo.sdk.BaseResponse;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by :Guozhihua
 * Date： 2017/8/17.
 */

@Api(value = "/b", description = "测试接口API Controller", basePath = "http://www.neudev.cn/", position = 1)
@RestController
@RequestMapping("/b")
@RefreshScope
public class BTestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("TestController");

    @Autowired
    private GatewayApi gatewayApi;

    @Autowired
    private ServiceCApi serviceCApi;

    @RequestMapping(value = "/hello/{userName}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse sayHello(@PathVariable("userName") String username, HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baseResponse.setData("serviceB-gray: hello " + username + ",version is " + request.getHeader("version"));
        baseResponse.setStatus("success");
        System.out.println(baseResponse.getData());
        return baseResponse;
    }

    @RequestMapping(value = "/hello_link/{userName}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse helloLink2C(@PathVariable("userName") String username, HttpServletRequest request) {
        BaseResponse baseResponse = gatewayApi.sayLinkHelloC("cross zuul "+username);
        return baseResponse;
    }

    @RequestMapping(value = "/hello_link1/{userName}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse helloLink2C1(@PathVariable("userName") String username, HttpServletRequest request) {
        BaseResponse baseResponse =serviceCApi .sayLinkHello("direct from b "+username);
        return baseResponse;
    }


}
