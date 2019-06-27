
package org.gitors.jobdemo.utils;


import org.gitors.jobdemo.entity.JobEntity;
import org.gitors.jobdemo.entity.JobLogEntity;
import org.gitors.jobdemo.service.JobLogService;
import org.gitors.jobdemo.service.JobService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.UUID;


/**
 * 通用任务
 * @author liuwenlong
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static JobService jobService;

	private static JobLogService jobLogService;

	/**
	 * 获取JobService 对象
	 * @return
	 */
	private JobService getJobService(){
		if (jobService == null){
			jobService = (JobService) SpringContextUtil.getBean("jobServiceImpl");
		}
		return jobService;
	}

	private JobLogService getJobLogService(){
		if (jobLogService == null){
			jobLogService = (JobLogService) SpringContextUtil.getBean("jobLogServiceImpl");
		}
		return jobLogService;
	}


    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobEntity scheduleJob = (JobEntity) context.getMergedJobDataMap()
        		.get(JobEntity.JOB_PARAM_KEY);

        //任务开始时间
        long startTime = System.currentTimeMillis();
		JobLogEntity jobLogEntity = new JobLogEntity();
		jobLogEntity.setId(UUID.randomUUID().toString().replace("-",""));
        
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobName());

			JobEntity jobEntity = this.getJobService().findById(scheduleJob.getId());
			jobLogEntity.setRanTime(new Date(startTime));
			if (jobEntity == null){
				//删除quartz 队列中的 任务
				this.getJobService().deleteJob(scheduleJob.getId());
				throw new RuntimeException("任务已经被删除");
			}
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobName() + "  总共耗时：" + times + "毫秒");
			jobLogEntity.setRanExpend(times);
			jobLogEntity.setStatus(0);
			jobLogEntity.setJobId(scheduleJob.getId());
			jobLogEntity.setRanNum(scheduleJob.getRetriedNum());
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobName(), e);
			jobLogEntity.setStatus(1);
			jobLogEntity.setMsg(e.getMessage());
			//需要重试
			if (scheduleJob.getRetry() == 1 && scheduleJob.getRestryNum() > scheduleJob.getRetriedNum()){
				logger.error("任务准备在" + scheduleJob.getRetryInterval() + "秒后重试。任务名:"+ scheduleJob.getJobName());
				this.getJobService().reTry(scheduleJob);
			}
		}finally {
			this.getJobLogService().save(jobLogEntity);
		}
    }
}
