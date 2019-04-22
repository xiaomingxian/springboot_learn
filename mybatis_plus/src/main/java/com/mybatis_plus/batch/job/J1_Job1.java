package com.mybatis_plus.batch.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class J1_Job1 {
    /**
     * 多job顺序执行
     */

    //    注入负责创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    //    任务由step执行
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //    创建任务对象
    @Bean("j1")
    public Job job1() {
        //on条件 from  to
        //jobBuilderFactory.get("job1")//任务名称
        //        .start(ste1())//任务执行者
        //        .on("COMPLATE").to(ste2()).from(ste2()).on("COMPLATE").to(ste3())
        //        .build();
        //

        return jobBuilderFactory.get("job1")//任务名称
                .start(ste1())//任务执行者
                .next(ste2())
                .next(ste3())
                .build();

    }

    public Step ste1() {
        return stepBuilderFactory.get("ste1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================> ste1执行 ");
                        return RepeatStatus.FINISHED;//返回正常的状态，下个任务才能正常执行
                    }
                }).build();
    }

    public Step ste2() {
        return stepBuilderFactory.get("ste2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================> ste2执行 ");
                        return RepeatStatus.FINISHED;//返回正常的状态，下个任务才能正常执行
                    }
                }).build();
    }

    public Step ste3() {
        return stepBuilderFactory.get("ste3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================> ste3执行 ");
                        return RepeatStatus.FINISHED;//返回正常的状态，下个任务才能正常执行
                    }
                }).build();
    }
}
