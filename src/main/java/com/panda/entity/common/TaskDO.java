package com.panda.entity.common;

import java.util.Date;
import javax.persistence.*;

@Table(name = "${task_table}")
public class TaskDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 任务URL
     */
    private String url;

    /**
     * 任务状态
     */
    @Column(name = "task_status")
    private Integer taskStatus;

    /**
     * 任务运行周期
     */
    @Column(name = "cycle_time")
    private Date cycleTime;

    /**
     * 上一次开始运行时间
     */
    @Column(name = "last_start_time")
    private Date lastStartTime;

    /**
     * 下一次开始运行时间
     */
    @Column(name = "next_start_time")
    private Date nextStartTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取任务ID
     *
     * @return task_id - 任务ID
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置任务ID
     *
     * @param taskId 任务ID
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取任务名称
     *
     * @return task_name - 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取任务URL
     *
     * @return url - 任务URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置任务URL
     *
     * @param url 任务URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取任务状态
     *
     * @return task_status - 任务状态
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态
     *
     * @param taskStatus 任务状态
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取任务运行周期
     *
     * @return cycle_time - 任务运行周期
     */
    public Date getCycleTime() {
        return cycleTime;
    }

    /**
     * 设置任务运行周期
     *
     * @param cycleTime 任务运行周期
     */
    public void setCycleTime(Date cycleTime) {
        this.cycleTime = cycleTime;
    }

    /**
     * 获取上一次开始运行时间
     *
     * @return last_start_time - 上一次开始运行时间
     */
    public Date getLastStartTime() {
        return lastStartTime;
    }

    /**
     * 设置上一次开始运行时间
     *
     * @param lastStartTime 上一次开始运行时间
     */
    public void setLastStartTime(Date lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    /**
     * 获取下一次开始运行时间
     *
     * @return next_start_time - 下一次开始运行时间
     */
    public Date getNextStartTime() {
        return nextStartTime;
    }

    /**
     * 设置下一次开始运行时间
     *
     * @param nextStartTime 下一次开始运行时间
     */
    public void setNextStartTime(Date nextStartTime) {
        this.nextStartTime = nextStartTime;
    }
}