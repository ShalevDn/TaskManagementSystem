package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository TaskRepository;

    public TaskService(){

    }

    public List<Task> getTasks(){
        return TaskRepository.findAll();
    }

    public boolean find(Long id){
        return TaskRepository.existsById(id);
    }

    public void DeleteTask(Long id){

        TaskRepository.deleteById(id);
    }

    public void addTask(Task t){

        TaskRepository.save(t);
    }

    public Task updateTask(Long id, Task newTask) {
        // inserting values in frontend, if value is empty then we don't update
        Task t = TaskRepository.findById(id).get();
        if (newTask.getTitle() != null && !newTask.getTitle().isBlank()) {
            t.setTitle(newTask.getTitle());
        }
        if (newTask.getDescription() != null && !newTask.getDescription().isBlank()) {
            t.setDescription(newTask.getDescription());
        }
        if (newTask.getStatus() != null) {
            t.setStatus(newTask.getStatus());
        }
        if (newTask.getPriority() != null) {
            t.setPriority(newTask.getPriority());
        }
        if (newTask.getDueDate() != null && !newTask.getDueDate().isBlank()) {
            t.setDueDate(newTask.getDueDate());
        }
        return TaskRepository.save(t);
    }


}
