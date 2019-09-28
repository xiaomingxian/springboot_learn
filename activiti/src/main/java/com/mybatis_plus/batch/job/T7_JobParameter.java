package com.mybatis_plus.batch.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class T7_JobParameter implements StepExecutionListener {
    /**
     * 参数设置
     * Program auguments test=my_parameter_test
     */


    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    Map<String, JobParameter> map;
    @Bean
    public Job jobPar() {
        return jobBuilderFactory.get("jobparameter")
                .start(step()).build();
    }

    private Step step() {
        return (Step) stepBuilderFactory.get("par-step")
                .listener(this)//当前类是step监听器
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("获取到参数" + map.get("test"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("==============>前置监听器");
        JobParameters jobParameters = stepExecution.getJobParameters();
        map = jobParameters.getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
