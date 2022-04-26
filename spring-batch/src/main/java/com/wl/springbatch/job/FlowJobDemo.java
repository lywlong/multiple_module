package com.wl.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/7 16:17
 */
@Component
public class FlowJobDemo {

    private JobBuilderFactory jobBuilderFactory;//任务创建工厂
    private StepBuilderFactory stepBuilderFactory;//步骤创建工厂

    @Autowired
    public FlowJobDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job flowJob(){
        return this.jobBuilderFactory.get("flow_job_demo")
                .start(flow())
                .next(stepThree())
                .end()
                .build();
    }

    private Flow flow(){
        return new FlowBuilder<Flow>("flow_demo")
                .start(stepOne())
                .next(stepTow())
                .build();
    }

    private Step stepOne() {
        return stepBuilderFactory.get("step_fist_flow")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flow执行第一步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepTow() {
        return stepBuilderFactory.get("step_seconds_flow")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flow执行第2步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepThree() {
        return stepBuilderFactory.get("step_third_flow")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flow执行第3步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
