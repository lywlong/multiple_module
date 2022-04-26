package com.wl.springbatch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/7 15:22
 */
@Component
public class MultiStepJobDemo {

    private JobBuilderFactory jobBuilderFactory;//任务创建工厂
    private StepBuilderFactory stepBuilderFactory;//步骤创建工厂

    @Autowired
    public MultiStepJobDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job multiJob() {
        return jobBuilderFactory.get("multi_job")
                .start(stepOne())
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepTow()).from(stepTow())
                .on(ExitStatus.COMPLETED.getExitCode()).to(stepThree()).from(stepThree())
                //.next(stepTow())
                //.next(stepThree())
                .end()
                .build();
    }

    private Step stepOne() {
        return stepBuilderFactory.get("step_fist")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("执行第一步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepTow() {
        return stepBuilderFactory.get("step_seconds")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("执行第2步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepThree() {
        return stepBuilderFactory.get("step_third")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("执行第3步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
