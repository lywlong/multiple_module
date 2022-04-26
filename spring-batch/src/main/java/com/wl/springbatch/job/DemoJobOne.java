package com.wl.springbatch.job;

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
 * @date 2022/4/7 15:02
 */
@Component
public class DemoJobOne {

    private JobBuilderFactory jobBuilderFactory;//任务创建工厂
    private StepBuilderFactory stepBuilderFactory;//步骤创建工厂

    @Autowired
    public DemoJobOne(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job demoJob(){
        return jobBuilderFactory.get("one_job")
                .start(step()).build();
    }

    private Step step(){
        return stepBuilderFactory.get("one_step")
                .tasklet((contribution,chunkContext)->{
                    System.out.println("执行步骤。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
