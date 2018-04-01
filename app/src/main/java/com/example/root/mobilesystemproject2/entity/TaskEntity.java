package com.example.root.mobilesystemproject2.entity;

import com.orm.SugarRecord;

import java.sql.Date;

enum TaskPriority {
    LOW, MEDIUM, HIGHT
}

public class TaskEntity extends SugarRecord<TaskEntity> {
    private Date addDate;
    private String name;
    private Date endDate;
    private TaskPriority priority;
    private String description;

    public TaskEntity() {}

    public TaskEntity(
            Date addDate,
            String name,
            Date endDate,
            TaskPriority priority,
            String description
    ) {
        this.addDate = addDate;
        this.name = name;
        this.endDate = endDate;
        this.priority = priority;
        this.description = description;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
