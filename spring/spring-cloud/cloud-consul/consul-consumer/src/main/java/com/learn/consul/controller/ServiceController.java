/**
 * @author G-bug 2019/12/2
 */
package com.learn.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @auth Administrator
 */
@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances("service-producer");
    }

    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("service-producer").getUri().toString();
    }

    @RequestMapping("/hello")
    public String callHello() {

        String.class.getMethods();


        ArrayList<String> cc = new ArrayList<String>();



        Thread a = new Thread(() -> {

        });
        a.start();

        ServiceInstance serviceInstance = loadBalancer.choose("service-producer");
        System.out.println("服务地址:" + serviceInstance.getUri());
        return new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
    }
}
