package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.SubjectService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Controller for {@link com.artmal.edu_tool_for_youtube.model.Subject}'s pages.
 *
 * @author Artem Malchenko
 * @version 1.0
 */

@Controller
public class SubjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PlaylistService playlistService;

    @RequestMapping(value = "/list-of-subjects", method = RequestMethod.GET)
    public String showSubjectsPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        Set<Subject> listOfSubjects = subjectService.findAllByUser(currentUser);
        model.addAttribute("listOfSubjects", listOfSubjects);
        return "subjectsPage";
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String showSubjectPage(Model model, @RequestParam("subject_id") long subjectId) {
        Subject subject = subjectService.findById(subjectId);

        Set<Playlist> listOfPlaylists = playlistService.findAllBySubject(subject);

        model.addAttribute("listOfPlaylists", listOfPlaylists);
        model.addAttribute("subject_title", subject.getTitle());
        return "subjectPage";
    }
}