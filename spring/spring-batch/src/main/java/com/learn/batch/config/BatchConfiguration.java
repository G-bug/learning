package com.learn.batch.config;

import com.learn.batch.entity.Person;
import com.learn.batch.exception.MyJobException;
import com.learn.batch.lisenter.AnnotationStepListener;
import com.learn.batch.lisenter.JobListener;
import com.learn.batch.lisenter.StepSkipListener;
import com.learn.batch.processor.CustomProcessor;
import io.micrometer.core.instrument.binder.db.PostgreSQLDatabaseMetrics;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

/**
 * @author g-bug
 * @date 2020/9/29 上午9:37
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public AnnotationStepListener annotationStepListener;

    @Autowired
    public StepSkipListener stepSkipListener;

    @Bean
    public Job personJob(JobListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2())
                .end()
                .build();
    }

    /**
     * 简单flow任务
     *
     * @return
     */
    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob")
                .start(flow())
                .end()
                .build();
    }

    /**
     * 关联 job 和 ioc容器
     * jobOperator 必需, 否则报错
     * No job configuration with the name [flowJob] was registered
     */
    @Bean
    public JobRegistryBeanPostProcessor flowProcessor(JobRegistry jobRegistry, ApplicationContext applicationContext) {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.setBeanFactory(applicationContext);
        return postProcessor;
    }

    /**
     * 并行步骤 -> split(flow)
     *
     * @return
     */
    @Bean
    public Job currentFlowJob() {
        return jobBuilderFactory.get("currentFlowJob")
                .start(flow())
                .split(new SimpleAsyncTaskExecutor()).add(flow1())
                .end()
                .build();
    }

    /**
     * 合成处理器
     */
    private CompositeItemProcessor<Person, Person> compositeItemProcessor() throws Exception {
        CompositeItemProcessor<Person, Person> processor = new CompositeItemProcessor<>();
        // 代理 多个处理器 顺序一层一层执行
        processor.setDelegates(Arrays.asList(processor(), validatingProcessor()));
        return processor;
    }

    @Bean
    public CustomProcessor processor() {
        return new CustomProcessor();
    }

    // jsr 校验
    private BeanValidatingItemProcessor<Person> validatingProcessor() throws Exception {
        return new BeanValidatingItemProcessor<Person>() {{
            // false 则不过滤不合规则数据(jsr-303)直接抛出异常
            setFilter(true);
            afterPropertiesSet();
        }};
    }

    /**
     * 读取数据
     */
    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("batch-data.csv"))
                .delimited()
                .names("name", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
        // 跳过 第一行
        reader.setLinesToSkip(1);
        return reader;
    }

    /**
     * FlatFileItemWriter 文本文件写出
     * StaxEventItemWriter Xml文件写出
     * JsonItemWriter json文件写出
     */
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                // 映射 对象属性到 sql语句占位符
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into person (name, age) values(:name, :age)")
                .dataSource(dataSource)
                .build();
    }

    /**
     * flow->一系列步骤的组合
     */
    private Flow flow() {
        return new FlowBuilder<Flow>("flow")
                .start(step2())
                .next(step3())
                .build();
    }

    private Flow flow1() {
        return new FlowBuilder<Flow>("flow")
                .start(step4())
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer) throws Exception {
        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        attribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
        attribute.setTimeout(100);

        return stepBuilderFactory.get("step1")
                .listener(annotationStepListener)
                // chunk 类似tasklet() chunkSize->指定读取多少数据再做输出
                .<Person, Person>chunk(10)

                // 开启容错机制, chunkListener要与该方法配套
                .faultTolerant()
                // 容许的异常
                .skip(MyJobException.class)
                // 容许的异常出现次数(该次数是 reader processor writer中的异常次数总合)
                .skipLimit(1)
                // skip 的详细情况拦截
                .listener(stepSkipListener)

                // reader() 指定读取数据的方式
                .reader(reader())
                // 错误后 重读已reader的信息
                .readerIsTransactionalQueue()
                // 自定义处理
                .processor(processor())
                // 多处理器 (重复会覆盖)
                .processor(compositeItemProcessor())
                .writer(writer)

                // 增加事务支持
                .transactionAttribute(attribute)

                // 默认 step completed则不会再执行, 该方法解除限制, 每次重启都会再次执行
                .allowStartIfComplete(true)
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                // 步骤任务块
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step2.....");
                    // 读取参数
                    StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
                    Map<String, JobParameter> jobParameterMap = stepExecution.getJobParameters().getParameters();
                    System.out.println("输出 jobLauncher传递的参数:" + jobParameterMap.get("message"));

                    //执行上下文
                    ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
                    if (executionContext.containsKey("success")) {
                        System.out.println("step2 执行成功......");
                        return RepeatStatus.FINISHED;
                    } else {
                        // 配合DB持久化该参数下次执行会生效, 从中断处step继续执行
                        //  executionContext.put("success", true);
                        // 抛出异常 中断执行
                        throw new RuntimeException("step2 执行异常......");
                    }
                })
                // 处理先决任务, 执行x次之后再次重启job执行此处则抛出StartLimitExceededException
                .startLimit(2)
                .build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step3......");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step4......");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
