package com.learn.batch.config;

import com.learn.batch.adapter.ReadAdapter;
import com.learn.batch.entity.ReportIndex;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author g-bug
 * @date 2020/10/9 下午5:45
 */
@Configuration
@EnableBatchProcessing
public class IndexBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public ReadAdapter readAdapter;

    @Bean
    public Job indexJob() throws Exception {
        return jobBuilderFactory.get("indexJob")
                .start(step1())
                .build();
    }

    private Step step1() throws Exception {
        return stepBuilderFactory.get("readerStep")
                .<ReportIndex, ReportIndex>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("proStep")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    /**
     * 读取
     */
    private ItemReader<ReportIndex> itemReader() throws Exception {

        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setFromClause("from t_rp_table_index_rule");
        provider.setSelectClause("select id, code, val, sql_str, dependency");
        provider.setSortKeys(new HashMap<String, Order>(1) {{
            put("id", Order.ASCENDING);
        }});

        JdbcPagingItemReader<ReportIndex> reader = new JdbcPagingItemReaderBuilder<ReportIndex>()
                .name("indexReader")
                .dataSource(dataSource)
                .queryProvider(provider)
                .pageSize(1)
                .build();

        reader.setRowMapper((set, num) -> new ReportIndex() {{
            setId(set.getInt(1));
            setCode(set.getString(2));
            setVal(set.getString(3));
            setSqlStr(set.getString(4));
            setDependency(set.getString(5));
        }});

        reader.afterPropertiesSet();
        return reader;
    }

    /**
     * 写入
     */
    private ItemWriter<ReportIndex> itemWriter() {
        JdbcBatchItemWriter<ReportIndex> writer = new JdbcBatchItemWriterBuilder<ReportIndex>()
                // 映射 对象属性到 sql语句占位符
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("update t_rp_table_index_rule set val=:val where id=:id")
                .dataSource(dataSource)
                .build();
        writer.afterPropertiesSet();
        return writer;
    }
}
