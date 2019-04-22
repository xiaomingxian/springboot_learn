package com.mybatis_plus.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class J2_FlowDemo {
    /**
     * step放到flow,flow放到job里
     */

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean("flow-job")
    public Job job() {

        return jobBuilderFactory.get("job-flow").start(getFlow()).next(step3()).end().build();
    }


    @Bean
    public Flow getFlow() {
        return new FlowBuilder<Flow>("flow1").start(step1()).next(step2()).build();
    }


    private Step step1() {
        return stepBuilderFactory.get("step-2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("-------step-2执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    public Step step2() {

        return stepBuilderFactory.get("step-1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("-------step-1执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    public Step step3() {

        return stepBuilderFactory.get("step-3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("-------step-3执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
