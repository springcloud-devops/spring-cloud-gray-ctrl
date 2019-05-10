package serviceC.controller;

import demo.sdk.BaseResponse;
import demo.sdk.utils.HttpUtils;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by :Guozhihua
 * Date： 2017/8/17.
 */

@Api(value = "/c", description = "测试接口API Controller", basePath = "http://www.neudev.cn/", position = 1)
@RestController
@RequestMapping("/c")
@RefreshScope
public class CTestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("TestController");


    @RequestMapping(value = "/hello_link/{userName}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse sayHello(@PathVariable("userName") String username, HttpServletRequest request) {

        try {
            String tsy=null;
            String from = HttpUtils.getFrom("http://172.21.2.229:8000", null);
            System.out.println("**********from********************");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setData("serviceC: hi,this is service C-demoxxxx ,welcome  "+username+",version is "+request.getHeader("version"));
        baseResponse.setStatus("success");
        System.out.println(baseResponse.getData());
        return baseResponse;
    }


}
