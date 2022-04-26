package com.wl.springbatch.job;

import com.wl.springbatch.decider.CustomDecider;
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
 * @date 2022/4/7 16:35
 */
@Component
public class DeciderJobDemo {

    private JobBuilderFactory jobBuilderFactory;//任务创建工厂
    private StepBuilderFactory stepBuilderFactory;//步骤创建工厂
    private CustomDecider customDecider;//自定义任务决策

    @Autowired
    public DeciderJobDemo(JobBuilderFactory jobBuilderFactory,
                          StepBuilderFactory stepBuilderFactory,
                          CustomDecider customDecider) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.customDecider = customDecider;
    }

    @Bean
    public Job customDeciderJob(){
        return this.jobBuilderFactory.get("decider_job")
                .start(stepOne())
                .next(customDecider)
                .from(customDecider).on("Thursday").to(stepTow())
                .from(customDecider).on("workDay").to(stepThree())
                .from(stepThree()).on("*").to(stepFour())
                .end().build();
    }

    private Step stepOne() {
        return stepBuilderFactory.get("step_fist_decider")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("decider策略下，执行第一步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepTow() {
        return stepBuilderFactory.get("step_seconds_decider")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("decider策略下，执行第2步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepThree() {
        return stepBuilderFactory.get("step_third_decider")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("decider策略下，执行第3步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step stepFour() {
        return stepBuilderFactory.get("step_four_decider")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("decider策略下，执行第4步。。。 ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
