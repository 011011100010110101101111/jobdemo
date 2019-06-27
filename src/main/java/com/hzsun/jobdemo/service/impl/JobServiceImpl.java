package com.hzsun.jobdemo.service.impl;

import com.hzsun.jobdemo.dao.JobDao;
import com.hzsun.jobdemo.entity.JobEntity;
import com.hzsun.jobdemo.service.JobService;
import com.hzsun.jobdemo.utils.Jobutils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author : liuwenlong
 * @desc : 定时任务service
 * @company : 正元智慧
 * @date : 2019-06-26 15:05
 */
@Service
public class JobServiceImpl implements JobService {

    private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private JobDao jobDao;

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init(){
        logger.info("--------初始化定时任务----------");
        List<JobEntity> jobEntities = jobDao.findAll();
        logger.info("---------正在初始化"+jobEntities.size()+"个定时任务-----------");
        jobEntities.forEach(jobEntity -> {
            //初始化定时任务，如果已经在quartz中，则不重复初始化，如果不在quartz，则进行初始化
            CronTrigger cronTrigger = Jobutils.getCronTrigger(scheduler,jobEntity.getId());
            if (cronTrigger == null){
                System.out.println("创建一条任务");
                Jobutils.createScheduleJob(scheduler,jobEntity);
            }
            //TODO 如果在quartz队列还在，但是在数据库任务配置表已经删除的，则从队列移除任务。
        });
        logger.info("----------定时任务初始化成功----------");
    }

    @Override
    public List<JobEntity> findAll() {
        return jobDao.findAll();
    }

    @Override
    public JobEntity findById(String id) {
        return jobDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void reTry(JobEntity jobEntity) {
        if (jobEntity.getRetriedNum() < jobEntity.getRestryNum()){
            jobEntity.setRetriedNum(jobEntity.getRetriedNum() + 1);
        }
        jobDao.save(jobEntity);
        Jobutils.deleteScheduleJob(scheduler,jobEntity.getId());
        Jobutils.retry(scheduler,jobEntity);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteJob(String id) {
        Jobutils.deleteScheduleJob(scheduler,id);
        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(id);
        jobDao.delete(jobEntity);
    }
}
