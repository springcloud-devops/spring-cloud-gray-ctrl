package api;


import demo.sdk.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by :Guozhihua
 * Date： 2017/8/28.
 */
@FeignClient(value = "serviceA")
@RequestMapping(value = "/api/")
public interface ServiceAApi {


    /**
     * 这里的 postMapping getMapping 都是无效的
     * 只有 requestMapping 有效
     * @param name
     * @return
     */
    @RequestMapping(value = "test/login/{userName}",method = RequestMethod.POST)
    BaseResponse sayHello(@PathVariable(value = "userName") String name);
}
