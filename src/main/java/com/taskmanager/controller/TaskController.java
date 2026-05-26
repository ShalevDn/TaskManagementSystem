package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService TaskService;

    @GetMapping("/tasks")
    @ResponseBody
    public List<Task> getTasks() {

        return TaskService.getTasks();
    }

//    @PostMapping("/tasks/create/{id}/{title}/{description}/{dueDate}")
//    public ResponseEntity<String> CreateTask(@PathVariable long id, @PathVariable String title, @PathVariable String description, @PathVariable String dueDate) {
//        TaskService.addTask(new Task(id, title, description, dueDate));
//        return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully");
//    }

    @PostMapping("/tasks")
    public ResponseEntity<String> CreateTask(@RequestBody Task t) {
        if(TaskService.find(t.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID already exist!");
        }
        TaskService.addTask(t);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task has been created!");
    }
    @PutMapping("/tasks/{id}")
    public ResponseEntity<String> EditTask(@RequestBody Task t, @PathVariable long id) {
        if (!TaskService.find(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
        }
        t.setId(id);
        TaskService.updateTask(id, t);
        return ResponseEntity.status(HttpStatus.OK).body("Task has been edited!");
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> DeleteTask(@PathVariable long id) {
        if(TaskService.find(id)){
            TaskService.DeleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find task");

    }


}
