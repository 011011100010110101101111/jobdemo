package org.gitors.jobdemo.entity;


import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : liuwenlong
 * @desc : 任务实体
 * @date : 2019-06-26 14:41
 */
@Entity
@Proxy(lazy = false)
public class JobEntity implements Serializable {

    private static final long serialVersionUID = -8680221003366638822L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @Id
    private String id;

    /**
     * 任务名
     */
    private String jobName;
    /**
     * 数据源ID
     */
    private String sourceId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 当前状态： 1-正常，2-暂停，3-已删除
     */
    private Integer status;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 失败重试次数
     */
    private int restryNum;
    /**
     * 是否失败重试 1-是，0 否
     */
    private Integer retry;
    /**
     * 已经重试的次数
     */
    private int retriedNum;

    /**
     * 重试间隔时间：单位-秒
     */
    private int retryInterval;
    /**
     * 目标库ID
     */
    private String targetId;

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public int getRestryNum() {
        return restryNum;
    }

    public void setRestryNum(int restryNum) {
        this.restryNum = restryNum;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public int getRetriedNum() {
        return retriedNum;
    }

    public void setRetriedNum(int retriedNum) {
        this.retriedNum = retriedNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JobEntity{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", cronExpression='" + cronExpression + '\'' +
                ", restryNum=" + restryNum +
                ", retry=" + retry +
                ", retriedNum=" + retriedNum +
                ", targetId='" + targetId + '\'' +
                '}';
    }
}
