## spring cloud 灰度发布（金丝雀部署组件）

- 1. spring cloud 版本目前基于 Greenwich.RELEASE
- 2. boot 的版本是2.1.0.RELEASE 版本
- 3. 基于的思路是添加Ribbon路由规则策略，实现灰度实例和正常实例的调度区分
- 4. 灰度发布需要管理功能，则需要控制台进行灰度打标，前端调用通过header来决定调用链路的选择
- 5. FeignClient 方式进行链路调度的header传递