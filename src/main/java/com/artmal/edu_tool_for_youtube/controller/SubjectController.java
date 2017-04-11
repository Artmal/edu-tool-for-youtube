package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.SubjectService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class SubjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/list-of-subjects", method = RequestMethod.GET)
    public String showSubjectsPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        Set<Subject> listOfSubjects = subjectService.findAllByUser(currentUser);
        model.addAttribute("listOfSubjects", listOfSubjects);
        return "subjectsPage";
    }
}
