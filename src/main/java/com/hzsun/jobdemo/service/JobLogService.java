package com.hzsun.jobdemo.service;

import com.hzsun.jobdemo.entity.JobLogEntity;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc :
 * @company : 正元智慧
 * @date : 2019-06-26 15:05
 */
public interface JobLogService {
    /**
     * 保存
     */
    void save(JobLogEntity jobLogEntity);

    /**
     *
     * @return
     */
    List<JobLogEntity> list();
}
