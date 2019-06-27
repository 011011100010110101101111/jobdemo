package com.hzsun.jobdemo.dao;

import com.hzsun.jobdemo.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : liuwenlong
 * @desc :
 * @company : 正元智慧
 * @date : 2019-06-26 15:06
 */
public interface JobDao extends JpaRepository<JobEntity,String> {

}
