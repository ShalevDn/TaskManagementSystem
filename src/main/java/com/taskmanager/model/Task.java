package com.taskmanager.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumeratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task{
    @Id
    private Long id;
    private String title;
    private String description;
    @EnumeratedValue
    private TaskStatus status = TaskStatus.TODO;
    @EnumeratedValue
    private TaskPriority priority = TaskPriority.MEDIUM;
    private String dueDate;

    //Task Constructors
    public Task(){

    }
    public Task(Long id, String title, String description, String dueDate){
        this.id= id;
        this.title= title;
        this.description= description;
        this.dueDate= dueDate;
    }


    //Getters And Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}

