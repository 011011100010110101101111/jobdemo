package com.hzsun.jobdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : liuwenlong
 * @desc : 任务日志实体
 * @company : 正元智慧
 * @date : 2019-06-26 14:49
 */
@Entity
public class JobLogEntity implements Serializable {

    private static final long serialVersionUID = -6348228864839928728L;

    @Id
    private String id;
    /**
     * 运行时间
     */
    private Date ranTime;
    /**
     * 运行状态
     */
    private Integer status;
    /**
     * 运行信息
     */
    private String msg;
    /**
     * 第几次运行：默认运行为第0次运行。
     */
    private Integer ranNum;
    /**
     * 作业ID
     */
    private String jobId;

    /**
     * 运行耗时
     */
    private Long ranExpend;

    public Long getRanExpend() {
        return ranExpend;
    }

    public void setRanExpend(Long ranExpend) {
        this.ranExpend = ranExpend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRanTime() {
        return ranTime;
    }

    public void setRanTime(Date ranTime) {
        this.ranTime = ranTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRanNum() {
        return ranNum;
    }

    public void setRanNum(Integer ranNum) {
        this.ranNum = ranNum;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "JobLogEntity{" +
                "id='" + id + '\'' +
                ", ranTime=" + ranTime +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", ranNum=" + ranNum +
                ", jobId='" + jobId + '\'' +
                ", ranExpend=" + ranExpend +
                '}';
    }
}
