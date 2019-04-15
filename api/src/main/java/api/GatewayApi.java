package api;


import api.hystrix.GatewayHystrix;
import demo.sdk.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by :Guozhihua
 * Date： 2017/8/28.
 */
@FeignClient(value = "gateway",path = "/api/",fallback = GatewayHystrix.class)
public interface GatewayApi {

    @RequestMapping(value = "b/b/hello/{userName}",method = RequestMethod.GET)
    BaseResponse sayHelloB(@PathVariable(value = "userName")String name);

    @RequestMapping(value="b/b/hello_link/{userName}",method = RequestMethod.GET)
    BaseResponse sayLinkHelloB(@PathVariable(value = "userName")String name);

    @RequestMapping(value = "c/c/hello_link/{userName}",method = RequestMethod.GET)
    BaseResponse sayLinkHelloC(@PathVariable(value = "userName") String name);

}
