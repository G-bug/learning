package com.learn.batch.processor;

import com.learn.batch.entity.ReportIndex;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * 数据处理实现
 * BeanValidatingItemProcessor bean 核验处理
 *
 * @author g-bug
 * @date 2020/9/29 上午9:46
 */
@Component
public class IndexProcessor implements ItemProcessor<ReportIndex, ReportIndex> {

    @Override
    public ReportIndex process(ReportIndex reportIndex) {
        System.out.println("processor ......." + reportIndex.getCode());
        return reportIndex;
    }
}