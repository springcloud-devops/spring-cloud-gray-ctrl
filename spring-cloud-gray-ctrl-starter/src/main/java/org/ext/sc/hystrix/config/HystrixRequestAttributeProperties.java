package org.ext.sc.hystrix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hystrix1.propagate.request-attribute")
public class HystrixRequestAttributeProperties {
    /** Enable Hystrix propagate http request and response. Defaults to false. */
    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
