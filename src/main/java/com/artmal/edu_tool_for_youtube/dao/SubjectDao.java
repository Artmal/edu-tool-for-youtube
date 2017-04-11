package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SubjectDao extends JpaRepository<Subject, Long> {
    Set<Subject> findAllByUser(User user);
}
