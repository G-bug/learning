package com.learn.batch.repeat;

import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.stereotype.Component;

/**
 * 重复操作
 *
 * @author g-bug
 * @date 2020/10/9 下午3:28
 */
@Component
public class CustomerRepeat extends RepeatTemplate {

    @Override
    public RepeatStatus iterate(RepeatCallback callback) {
        return super.iterate((context) -> {
            System.out.println("repeat ...");
            return RepeatStatus.FINISHED;
        });
    }
}
