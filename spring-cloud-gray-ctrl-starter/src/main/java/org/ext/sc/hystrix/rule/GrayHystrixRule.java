package org.ext.sc.hystrix.rule;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.netflix.zuul.context.RequestContext;
import org.ext.sc.hystrix.utils.RequestContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class GrayHystrixRule extends ZoneAvoidanceRule {

    private Logger logger = LoggerFactory.getLogger(GrayHystrixRule.class);

    @Override
    public Server choose(Object key) {
        System.out.println("get Thread is : "+Thread.currentThread().getName());
        RequestContext requestContext = RequestContextHelper.get();
        String version = requestContext.getRequest().getHeader("version");
//        String version = "2.0";
        RequestContextHelper.remove();
        logger.info("grayRule key is : {},version is ",key,version);
        if(!StringUtils.isEmpty(version)){
            List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
            for (Server server : serverList) {
                logger.info("grayRule server is : {}",server);
                Map<String, String> metadata;
                metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
                String metaVersion = metadata.get("version");
                logger.info("grayRule metaVersion is : {}",metaVersion);
                if (!StringUtils.isEmpty(metaVersion)) {
                    if (metaVersion.equals(version)) {
                        return server;
                    }
                }
            }
        }
        return super.choose(key);
    }
}
