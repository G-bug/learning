package com.learn.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author g-bug
 * @date 2020/9/30 下午1:38
 */
@RestController
public class BatchController {

    @Autowired
    private Job currentFlowJob, personJob, indexJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("i")
    public String indexBatch() throws Exception {
        jobLauncher.run(indexJob, new JobParameters());
        return "success";
    }

    @GetMapping("person")
    public String personBatch(String message) throws Exception {
        JobParameters jobParameter = new JobParametersBuilder()
                .addString("message", message)
                .toJobParameters();
        jobLauncher.run(personJob, jobParameter);
        return "success";
    }


    @GetMapping
    public String executionBatch(String message) throws Exception {

        JobParameters jobParameter = new JobParametersBuilder()
                .addString("message", message)
                .toJobParameters();

        jobLauncher.run(currentFlowJob, jobParameter);

        return "success";
    }

    @GetMapping("23")
    public String executionOperatorJob(String message) throws Exception {
        jobOperator.start("flowJob", "message=" + message);
        return "success";
    }

}
