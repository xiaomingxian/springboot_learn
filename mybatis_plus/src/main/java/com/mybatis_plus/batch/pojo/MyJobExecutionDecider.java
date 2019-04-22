package com.mybatis_plus.batch.pojo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class MyJobExecutionDecider implements JobExecutionDecider {

    private int count;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if (count % 2 == 0) {
            System.out.println("===========>偶数：" + count);
            return new FlowExecutionStatus("aaa");
        }
        else {
            System.out.println("===========>奇数：" + count);
            return new FlowExecutionStatus("bbb");
        }

    }
}
