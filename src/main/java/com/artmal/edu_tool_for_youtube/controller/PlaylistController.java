package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class PlaylistController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/playlist/change", method = RequestMethod.GET)
    public String changePlaylist(Model model, @RequestParam("id") long videoId) {
        videoService.changeValueOfCompleteness(model, videoId);
        return "playlistPage";
    }

    @Transactional
    @RequestMapping(value = "/playlist/delete", method = RequestMethod.GET)
    public String deletePlaylist(Model model, @RequestParam("id") long playlistId) {
        playlistService.deleteById(playlistId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());
        Set<Playlist> playlistList = playlistService.findAllByUsers(loggedInUser);

        model.addAttribute("listOfPlaylists", playlistList);
        return "pageWithListOfPlaylists";
    }
}