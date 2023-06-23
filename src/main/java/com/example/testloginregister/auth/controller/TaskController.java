package com.example.testloginregister.auth.controller;

import com.example.testloginregister.repository.ProjectRepository;
import com.example.testloginregister.repository.TaskRepository;
import com.example.testloginregister.user.Project;
import com.example.testloginregister.user.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/{projectId}/task")
    public ResponseEntity<List<Task>> getProjectTasks(@PathVariable("projectId") Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + projectId + " not found"));

        List<Task> taskList = taskRepository.findByProjectId(projectId);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping("/{projectId}/task")
    public ResponseEntity<Task> addTaskToProject(@PathVariable("projectId") Long projectId, @RequestBody Task taskRequest) {
        Task task = projectRepository.findById(projectId).map(project -> {
            taskRequest.setProject(project);
            return taskRepository.save(taskRequest);
        }).orElseThrow(() -> new EntityNotFoundException("Project with id = " + projectId + " not found"));

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }


    @DeleteMapping("/{projectId}/task/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + projectId + " not found"));
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
