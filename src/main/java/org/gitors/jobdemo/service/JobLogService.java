package org.gitors.jobdemo.service;

import org.gitors.jobdemo.entity.JobLogEntity;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc :
 * @date : 2019-06-26 15:05
 */
public interface JobLogService {
    /**
     * 保存
     */
    void save(JobLogEntity jobLogEntity);

    /**
     * 根据jobID查询
     * @param jobId 任务ID
     * @return
     */
    List<JobLogEntity> list(String jobId);
}
