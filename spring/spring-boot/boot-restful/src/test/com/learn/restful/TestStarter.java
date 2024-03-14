/**
 * @author G-bug 2019/9/29
 */
package com.learn.restful;

import com.learn.starter.service.ExampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auth Administrator
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStarter {

    @Autowired
    private ExampleService service;

    @Test
    public void test() {
        System.out.println(service.wrap("hello"));
    }
}
