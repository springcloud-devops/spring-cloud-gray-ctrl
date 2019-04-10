package serviceB.controller;

import demo.sdk.BaseResponse;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/hello/{userName}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse sayHello(@PathVariable("userName") String username) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setData("serviceB-gray: hello "+username);
        baseResponse.setStatus("success");
        System.out.println(baseResponse.getData());
        return baseResponse;
    }


}
