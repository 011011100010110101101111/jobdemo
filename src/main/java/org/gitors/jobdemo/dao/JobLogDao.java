package org.gitors.jobdemo.dao;

import org.gitors.jobdemo.entity.JobLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : liuwenlong
 * @desc : 任务日志仓储
 * @date : 2019-06-26 15:06
 */
public interface JobLogDao extends JpaRepository<JobLogEntity,String> {

}
