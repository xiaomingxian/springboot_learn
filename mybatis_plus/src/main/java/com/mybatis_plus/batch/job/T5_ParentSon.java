package com.mybatis_plus.batch.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.job.JobStep;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class T5_ParentSon {
    /**
     * 添加子任务-需要将任务转成step
     */

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("parentSon")
    public Job job() {

        return jobBuilderFactory.get("parent")
                .start(son1())
                .next(son2())
                .build();

    }

    /**
     * job类型的step
     *
     * @return
     */
    private Step son2() {
        return new JobStepBuilder(new StepBuilder("jobson2")).job(sonJ2()).build();
    }

    private Step son1() {
        return new JobStepBuilder(new StepBuilder("jobson1")).job(sonJ1()).build();
    }

    //job1
    public Job sonJ1() {

        return jobBuilderFactory.get("sonJ1")
                .start(st2())
                .build();

    }

    //job2
    public Job sonJ2() {

        return jobBuilderFactory.get("sonJ2")
                .start(st1())
                .build();

    }

    private Step st1() {
        return stepBuilderFactory.get("st1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=======>st1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


    private Step st2() {
        return stepBuilderFactory.get("st2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=======>st2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


}
