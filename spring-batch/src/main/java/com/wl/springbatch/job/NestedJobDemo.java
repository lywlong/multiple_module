package com.wl.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/7 16:49
 */
@Component
public class NestedJobDemo {

    private JobBuilderFactory jobBuilderFactory;//任务创建工厂
    private StepBuilderFactory stepBuilderFactory;//步骤创建工厂
    private JobLauncher jobLauncher;//运行器
    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    public NestedJobDemo(JobBuilderFactory jobBuilderFactory,
                         StepBuilderFactory stepBuilderFactory,
                         JobLauncher jobLauncher,
                         JobRepository jobRepository,
                         PlatformTransactionManager platformTransactionManager) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    //父任务
    @Bean
    public Job parentJob(){
        return this.jobBuilderFactory.get("parent_job")
                .start(childJobOneStep())
                .next(childJobTowStep())
                .build();
    }

    //将子任务1转换为特殊的步骤
    private Step childJobOneStep(){
        return new JobStepBuilder(new StepBuilder("child_job_one_step"))
                .job(childJobOne())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    //将子任务2转换为特殊的步骤
    public Step childJobTowStep(){
        return new JobStepBuilder(new StepBuilder("child_job_tow_step"))
                .job(childJobTow())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    //子任务一
    private Job childJobOne() {
        return this.jobBuilderFactory.get("child_job_one")
                .start(
                        stepBuilderFactory.get("child_job_one_step")
                        .tasklet((stepContribution,chunkContext)->{
                            System.out.println("fu子任务1执行步骤。。。 ");
                            return RepeatStatus.FINISHED;
                        }).build()
                ).build();
    }

    private Job childJobTow(){
        return this.jobBuilderFactory.get("child_job_tow")
                .start(stepBuilderFactory.get("child_job_tow_step").tasklet(
                        (stepContribution,chunkContext)->{
                            System.out.println("fu子任务2执行步骤。。。 ");
                            return RepeatStatus.FINISHED;
                        }
                ).build()).build();
    }

}
