package com.learn.batch.lisenter;

import com.learn.batch.entity.Person;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * 跳过的拦截器
 *
 * @author g-bug
 * @date 2020/10/9 上午10:53
 */
@Component
public class StepSkipListener implements SkipListener<Person, Person> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println("read skip..." + t.getMessage());
    }

    @Override
    public void onSkipInWrite(Person item, Throwable t) {
        System.out.println("write skip..." + t.getMessage());
    }

    @Override
    public void onSkipInProcess(Person item, Throwable t) {
        System.out.println("process skip..." + t.getMessage());
    }
}
