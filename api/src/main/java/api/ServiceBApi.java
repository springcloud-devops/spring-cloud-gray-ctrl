package api;

import api.hystrix.ServiceBApiHystrix;
import demo.sdk.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 新版本不能再类上定义 RequestMapping，否则会出现mvc mapping错误
 * Created by :Guozhihua
 * Date： 2017/12/15.
 */


@FeignClient(value = "serviceB",path = "/b",fallback = ServiceBApiHystrix.class)
public interface ServiceBApi {


    @RequestMapping(value = "/hello/{userName}",method = RequestMethod.GET)
    BaseResponse sayHello(@PathVariable(value = "userName")String name);


    @RequestMapping(value = "/hello_link/{userName}",method = RequestMethod.GET)
    BaseResponse sayLinkHello(@PathVariable(value = "userName")String name);
}
