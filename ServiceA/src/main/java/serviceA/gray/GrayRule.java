package serviceA.gray;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class GrayRule extends ZoneAvoidanceRule {


    private Logger logger = LoggerFactory.getLogger(GrayRule.class);

    @Override
    public Server choose(Object key) {
        logger.info("grayRule key is : {},",key);
        //2.获取服务实例列表
        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        //3.循环serverList，选择version匹配的服务并返回
        for (Server server : serverList) {
            logger.info("grayRule server is : {}",server);
            Map<String, String> metadata;
            metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
            String metaVersion = metadata.get("version");
            logger.info("grayRule metaVersion is : {}",metaVersion);
            if (!StringUtils.isEmpty(metaVersion)) {
                if (metaVersion.equals("2.0")) {
                    return server;
                }
            }
        }

        return super.choose(key);
    }
}
