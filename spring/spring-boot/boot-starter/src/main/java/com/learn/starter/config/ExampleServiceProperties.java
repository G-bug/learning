/**
 * @author G-bug 2019/9/30
 */
package com.learn.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auth Administrator
 */
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
