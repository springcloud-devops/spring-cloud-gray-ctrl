## spring cloud 灰度发布（金丝雀部署组件）

- 1. spring cloud 版本目前基于 Greenwich.RELEASE
- 2. boot 的版本是2.1.0.RELEASE 版本
- 3. 基于的思路是添加Ribbon路由规则策略，实现灰度实例和正常实例的调度区分
- 4. 灰度发布需要管理功能，则需要控制台进行灰度打标，前端调用通过header来决定调用链路的选择
- 5. FeignClient 方式进行链路调度的header传递
- 6. 本项目demo用例不直接依赖官方的springboot parent依赖，依赖于dependencies，所以在使用过程中可能存在差异
      但是在使用的过程中，直接引入gray-ctrl-starter即可。
- 7. 参阅文档：[spring cloud 官方文档](https://spring.io/projects/spring-cloud)
### hystrix影响获取header的问题
```$txt
   hystrix 对于每一个服务启动了一个线程池做调用，如果启动了线程池，则其实请求会复制到对应的hystrix 的线程里，
   这时候再去调用ribbon 做负载均衡是获取不到线程变量的，即此时的 RequestContext ，RequestHoder 等使用ThreadLocal
   是无效的。可以使用过滤器或者定义hystrix 策略，使用hystrix的线程变量在ribbon 负载均衡的时候获取对应的自定义信息，
   从而实现定制化的负载均衡。
   

```
###  实现自定义hystrix 负载均衡策略
参阅文档：[hystrix传播对象](http://www.itmuch.com/spring-cloud-sum/hystrix-threadlocal/)
本组件采用了自定义hystrix实现了变量与hystrix的线程变量的绑定。通过FeignClientInterceptor
存放了header,这样在每一层的服务调度的时候，都可以传递header.

#### 参考：RequestAttributeHystrixConcurrencyStrategy
  这个类就是在hystrix的策略组里加入了自己的策略，然后对于主线程的requestContext进行了与
  hystrix 子线程的绑定。
  



