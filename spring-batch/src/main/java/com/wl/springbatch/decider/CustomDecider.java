package com.wl.springbatch.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/7 16:27
 */
@Component
public class CustomDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return new FlowExecutionStatus("Thursday");
        }else{
            return new FlowExecutionStatus("workDay");
        }
    }
}
