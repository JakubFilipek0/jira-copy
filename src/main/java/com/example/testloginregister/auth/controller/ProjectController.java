package com.example.testloginregister.auth.controller;

import com.example.testloginregister.repository.AppUserRepository;
import com.example.testloginregister.repository.ProjectRepository;
import com.example.testloginregister.user.AppUser;
import com.example.testloginregister.user.Project;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        if (projectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOneProjectById(@PathVariable(name = "id") Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + id + " not found"));

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project _project = projectRepository.save(project);
        return new ResponseEntity<>(_project, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProjectById(@PathVariable("id") Long id) {
        projectRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project projectRequest) {
        Project projectUpdate = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + id + " not found"));
        projectUpdate.setName(projectRequest.getName());
        projectUpdate.setDescription(projectRequest.getDescription());
        projectUpdate.setDateTimeHandOver(projectRequest.getDateTimeHandOver());
        projectRepository.save(projectUpdate);
        return new ResponseEntity<>(projectUpdate, HttpStatus.OK);
    }

    @PostMapping("/admin/{projectId}/user/{userId}")
    public ResponseEntity<?> addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + projectId + " not found"));
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + userId + " not found"));

        project.getUsers().add(appUser);
        appUser.getProjects().add(project);
        projectRepository.save(project);
        appUserRepository.save(appUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}/user")
    public ResponseEntity<List<AppUser>> getProjectAppUsers(@PathVariable("projectId") Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id = " + projectId + " not found"));

        List<AppUser> users = new ArrayList<>(project.getUsers());

        return ResponseEntity.ok(users);

    }

    @GetMapping("/user")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from endpoint");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
