package org.gitors.jobdemo.service.impl;

import org.gitors.jobdemo.dao.JobLogDao;
import org.gitors.jobdemo.entity.JobLogEntity;
import org.gitors.jobdemo.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc : 任务日志
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
    public List<JobLogEntity> list(String id) {
        return jobLogDao.findAllByJobId(id);
    }
}