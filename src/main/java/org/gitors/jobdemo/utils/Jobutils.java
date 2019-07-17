package org.gitors.jobdemo.utils;

import org.gitors.jobdemo.entity.JobEntity;
import org.quartz.*;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author : liuwenlong
 * @desc : 任务工具类
 * @date : 2019-06-26 15:29
 */
public class Jobutils {

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(jobId);
    }
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(jobId);
    }
    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, JobEntity jobEntity) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(jobEntity.getId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobEntity.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobEntity.getId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }

    /**
     * 重试
     * @param scheduler
     * @param jobEntity
     */
    public static void retry(Scheduler scheduler,JobEntity jobEntity){
        try {

            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(jobEntity.getId())).build();
            //表达式调度构建器
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().
                    withRepeatCount(jobEntity.getRestryNum() - jobEntity.getRetriedNum()).withIntervalInSeconds(jobEntity.getRetryInterval());

            //按新的cronExpression表达式构建一个新的trigger
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobEntity.getId())).withSchedule(simpleScheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.scheduleJob(jobDetail, trigger);
        }catch (Exception e){
            throw new RuntimeException("重试任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, JobEntity jobEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobEntity.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobEntity.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, jobEntity.getId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.rescheduleJob(triggerKey, trigger);

        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }

    public static void main(String[] args) {
        String cron="0 0/10 * * * ? ";  //每10分钟执行一次
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        Date currentTime = new Date();  //当前系统时间
        Date nextTimePoint = cronSequenceGenerator.next(currentTime);   //下次执行时间
        Date nextNextTimePoint = cronSequenceGenerator.next(nextTimePoint);  //下下次执行时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(currentTime);
        String nextTime=sdf.format(nextTimePoint);
        String nextDoubleTme=sdf.format(nextNextTimePoint);
        System.out.println("当前系统时间为："+time);
        System.out.println("下次执行时间为:"+nextTime);
        System.out.println("下下次执行时间为："+nextDoubleTme);
    }
}
