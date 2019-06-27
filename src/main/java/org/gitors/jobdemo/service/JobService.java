package org.gitors.jobdemo.service;

import org.gitors.jobdemo.entity.JobEntity;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc :
 * @date : 2019-06-26 15:05
 */
public interface JobService {
    /**
     * 查询所有
     * @return 任务列表
     */
    List<JobEntity> findAll();

    /**
     * 根据ID查询任务
     * @param id ID
     * @return
     */
    JobEntity findById(String id);

    /**
     * 重试
     * @param jobEntity
     */
    void reTry(JobEntity jobEntity);

    void deleteJob(String id);

    void save(JobEntity jobEntity);

    void update(JobEntity jobEntity);
}
