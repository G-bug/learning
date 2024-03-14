package com.learn.batch.lisenter;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

/**
 * 方法名必须和注解要求一致(详见源码)
 *
 * @author g-bug
 * @date 2020/9/30 上午11:06
 */
@Component
public class AnnotationStepListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("before ..." + stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println("after ..." + stepExecution.getStepName());
    }

}
