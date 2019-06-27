package org.gitors.jobdemo.dao;

import org.gitors.jobdemo.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : liuwenlong
 * @desc : 任务 仓储
 * @date : 2019-06-26 15:06
 */
public interface JobDao extends JpaRepository<JobEntity,String> {

}
