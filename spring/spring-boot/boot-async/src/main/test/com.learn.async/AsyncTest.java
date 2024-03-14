/**
 * @author G-bug 2019/10/16
 */
package com.learn.async;

import com.learn.async.task.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;

/**
 * @auth Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {

    @Autowired
    private AsyncTask task;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        task.dealNoReturnTask();
        Future<String> future = task.dealHaveReturnTask(10);
        assertThat(future.get(), anything("success"));
    }

}
