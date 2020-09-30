package com.learn.batch.config;

import com.learn.batch.entity.Person;
import com.learn.batch.lisenter.JobListener;
import com.learn.batch.processor.PersonProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;

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

    @Bean
    public PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into person values(:name, :age)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobListener listener, Step step1) {
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
    public Job importFlowJob() {
        return jobBuilderFactory.get("importFlowJob")
                .start(flow())
                .end()
                .build();
    }

    /**
     * 并行步骤 -> split(flow)
     *
     * @return
     */
    @Bean
    public Job importCurrentFlowJob() {
        return jobBuilderFactory.get("currentFlowJob")
                .start(flow())
                .split(new SimpleAsyncTaskExecutor()).add(flow1())
                .end()
                .build();
    }

    /**
     * flow->步骤组合
     *
     * @return
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
    public Step step1(JdbcBatchItemWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
                // chunk 类似tasklet() chunkSize->指定读取多少数据再做输出
                .<Person, Person>chunk(10)
                // reader() 指定读取数据的方式
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                // 步骤任务块
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step2.....");
                    return RepeatStatus.FINISHED;
                }).build();
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
