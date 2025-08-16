package com.example.worldlinetest.controller;

import com.example.worldlinetest.model.Task;
import com.example.worldlinetest.model.User;
import com.example.worldlinetest.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @GetMapping("/create-empty-task-list")
    public Integer createTaskList(int userId, String name) {
        return taskDao.createTaskList(userId, name, null);
    }

    @GetMapping("/create-task")
    public Integer createTask(Task task,int userId, int parentId) {
        int createdTaskId = taskDao.createTask(task);
        int updated = taskDao.createTaskTree(parentId, createdTaskId);
        return updated;
    }

    @GetMapping("/update-task")
    public Integer createTask(Task task) {
        return taskDao.updateTask(task);
    }

    @GetMapping("/delete-task")
    public Integer deleteTask(@RequestParam int taskId) {
        return taskDao.deleteTask(taskId);
    }

    @GetMapping("/delete-task-list")
    public Integer deleteTaskList(@RequestParam int taskIdParent) {
        return taskDao.deleteTaskList(taskIdParent);
    }

    @GetMapping("/move-task-to-task-list")
    public Integer moveTaskToTaskList(@RequestParam int newTaskIdParent, @RequestParam int oldTaskIdParent) {
        return taskDao.moveTaskToTaskList(newTaskIdParent, oldTaskIdParent);
    }

    @GetMapping("/all-lists-tasks")
    public List<User> getAllListsOfTasks(@RequestParam int userId) {
        return taskDao.getAllListsOfTasks(userId);
    }
}

