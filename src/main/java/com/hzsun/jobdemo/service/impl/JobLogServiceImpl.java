package com.hzsun.jobdemo.service.impl;

import com.hzsun.jobdemo.dao.JobLogDao;
import com.hzsun.jobdemo.entity.JobLogEntity;
import com.hzsun.jobdemo.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc : 任务日志
 * @company : 正元智慧
 * @date : 2019-06-26 15:05
 */
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    private JobLogDao jobLogDao;

    @Override
    public void save(JobLogEntity jobLogEntity) {
        jobLogDao.save(jobLogEntity);
    }

    @Override
    public List<JobLogEntity> list() {
        return jobLogDao.findAll();
    }
}