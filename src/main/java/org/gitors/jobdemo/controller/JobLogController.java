package org.gitors.jobdemo.controller;

import org.gitors.jobdemo.entity.JobLogEntity;
import org.gitors.jobdemo.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc : 日志controller
 * @date : 2019-06-27 15:46
 */
@RestController
@RequestMapping("log/")
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    /**
     * 根据JobId 查询所有日志
     * @param jobId 任务ID
     * @return
     */
    @RequestMapping("list/{jobId}")
    public List<JobLogEntity> list(@PathVariable String jobId){
        return jobLogService.list(jobId);
    }
}
