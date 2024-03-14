package com.learn.batch.lisenter;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * job监听器
 * beforeJob afterJob
 * stepExecutionListener
 * beforeStep afterStep
 * chunkExecutionListener
 * beforeChunk afterChunk afterChunkError
 * itemReaderListener
 * beforeRead afterRead onReadError
 * itemWriterListener
 * beforeProcess afterProcess onProcessError
 * itemProcessorListener
 * beforeWrite afterWrite onWriteError
 *
 * CompositeXXXExecutionListener 聚合监听器
 *
 * @author g-bug
 * @date 2020/9/29 上午9:32
 */
@Component
public class JobListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("job finished........");
        }
    }
}
