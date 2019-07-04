package org.gitors.jobdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : liuwenlong
 * @desc : 任务task VO 对应表ETL_JOB_TASK
 * @date : 2019-07-01 16:48
 */
@Entity
public class JobTaskVo implements Serializable {
    private static final long serialVersionUID = -8165194047645712265L;

    /**
     * id
     */
    @Id
    private String id;
    /**
     * Task 日期
     */
    private String taskDate;
    /**
     * 当前状态
     */
    private int status;
    /**
     * 任务ID
     */
    private String jobId;
    /**
     * 已经执行次数
     */
    private int ranNum;
    /**
     * 上一次执行时间
     */
    private Date lastRanTime;
    /**
     * 下一次执行时间。
     */
    private Date nextRunTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getLastRanTime() {
        return lastRanTime;
    }

    public void setLastRanTime(Date lastRanTime) {
        this.lastRanTime = lastRanTime;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRanNum() {
        return ranNum;
    }

    public void setRanNum(int ranNum) {
        this.ranNum = ranNum;
    }

    public Date getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(Date nextRunTime) {
        this.nextRunTime = nextRunTime;
    }
}
