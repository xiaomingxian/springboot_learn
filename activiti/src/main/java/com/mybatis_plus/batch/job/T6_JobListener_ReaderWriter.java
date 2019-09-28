package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.listener.MyChunkListener;
import com.mybatis_plus.batch.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class T6_JobListener_ReaderWriter {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job-listener-3")
                .start(step())
                .listener(new MyJobListener())
                .build();

    }

    private Step step() {
        return stepBuilderFactory.get("lis-step")
                .<String, String>chunk(2)//每读完几条数据再处理一次-----读写处理数据[读写类型]
                .faultTolerant()//容错
                .listener(new MyChunkListener())
                .reader(read())
                .writer(write())
                .build();

    }

    private ItemWriter<String> write() {
        //new ItemWriter<String>() {
        //    @Override
        //    public void write(List<? extends String> list) throws Exception {
        //
        //    }
        //};

        return (list) -> {
            for (String x : list) {
                System.out.println("====>"+x);
            }
        };
    }

    private ItemReader<String> read() {

        return new ListItemReader<>(Arrays.asList("hello", "batch", "java"));
    }


}
