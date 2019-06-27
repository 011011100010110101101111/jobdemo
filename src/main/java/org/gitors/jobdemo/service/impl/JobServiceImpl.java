package org.gitors.jobdemo.service.impl;

import org.gitors.jobdemo.dao.JobDao;
import org.gitors.jobdemo.entity.JobEntity;
import org.gitors.jobdemo.service.JobService;
import org.gitors.jobdemo.utils.Jobutils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author : liuwenlong
 * @desc : 定时任务service
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
                logger.info("初始化任务ID：" + jobEntity.getId());
                Jobutils.createScheduleJob(scheduler,jobEntity);
            }
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
//        Jobutils.deleteScheduleJob(scheduler,jobEntity.getId());
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(JobEntity jobEntity) {
        jobEntity.setId(UUID.randomUUID().toString().replace("-",""));
        jobDao.save(jobEntity);
        Jobutils.createScheduleJob(scheduler,jobEntity);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void update(JobEntity jobEntity) {
        jobDao.save(jobEntity);
        Jobutils.updateScheduleJob(scheduler,jobEntity);
    }
}
