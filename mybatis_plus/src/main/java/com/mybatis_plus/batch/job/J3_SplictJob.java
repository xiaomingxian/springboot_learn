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
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class J3_SplictJob {
    /**
     * split  并发执行
     */

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;


    @Bean("split-job")
    public Job job() {
        return jobBuilderFactory.get("split-job")
                .start(getFlow())
                .split(new SimpleAsyncTaskExecutor())//并发-异步
                .add(getFlow2())
                .end()
                .build();

        //执行结果
        //---------------->splict-step1执行
        //---------------->splict-step4执行
        //---------------->splict-step5执行
        //---------------->splict-step2执行
        //---------------->splict-step3执行
        //---------------->splict-step6执行
    }

    private Flow getFlow() {
        return new FlowBuilder<Flow>("flow-1").start(step1()).next(step2()).next(step3()).build();
    }

    private Flow getFlow2() {
        return new FlowBuilder<Flow>("flow-2").start(step4()).next(step5()).next(step6()).build();
    }

    private Step step4() {
        return stepBuilderFactory.get("split-step-4").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step4执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("split-step-3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step3执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


    private Step step2() {
        return stepBuilderFactory.get("split-step-2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step2执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private Step step1() {
        return stepBuilderFactory.get("split-step-1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step1执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private Step step5() {
        return stepBuilderFactory.get("split-step-5").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step5执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    private Step step6() {
        return stepBuilderFactory.get("split-step-6").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("---------------->splict-step6执行");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


}
