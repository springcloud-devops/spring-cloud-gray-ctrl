package serviceB.gray;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;

public class GrayRule extends ZoneAvoidanceRule {



    private Logger logger = LoggerFactory.getLogger(GrayRule.class);

    @Override
    public Server choose(Object key) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        String version = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader("version");
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
