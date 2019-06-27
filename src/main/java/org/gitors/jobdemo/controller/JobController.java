package org.gitors.jobdemo.controller;

import org.gitors.jobdemo.entity.JobEntity;
import org.gitors.jobdemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : liuwenlong
 * @desc : 任务controller
 * @date : 2019-06-27 15:46
 */
@RestController
@RequestMapping("job/")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("list")
    public List<JobEntity> list(){
        return jobService.findAll();
    }

    @RequestMapping("save")
    public String save(JobEntity jobEntity){
        try {
            jobService.save(jobEntity);
            return "成功";
        }catch (Exception e){
            return "失败";
        }
    }
}
