package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;

import java.util.Set;

public interface SubjectService {
    Subject findById(long id);
    Subject findByTitle(String title);

    Set<Subject> findAllByUser(User user);
}
