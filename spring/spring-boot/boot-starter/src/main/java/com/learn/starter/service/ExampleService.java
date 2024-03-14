/**
 * @author G-bug 2019/9/30
 */
package com.learn.starter.service;

/**
 * @auth Administrator
 */
public class ExampleService {
    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
