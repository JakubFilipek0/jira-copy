package com.example.testloginregister.repository;

import com.example.testloginregister.user.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
