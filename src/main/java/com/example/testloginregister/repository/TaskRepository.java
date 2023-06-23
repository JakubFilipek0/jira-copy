package com.example.testloginregister.repository;

import com.example.testloginregister.user.Project;
import com.example.testloginregister.user.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT p FROM Task p WHERE p.project.id = :id")
    List<Task> findByProjectId(@Param("id") Long id);

    @Transactional
    void deleteByProjectId(Long projectId);
}
