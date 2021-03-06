package org.ext.sc.hystrix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hystrix.propagate.request-attribute")
public class HystrixRequestAttributeProperties {
    /** Enable Hystrix propagate http request and response. Defaults to false. */
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
