package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import com.artmal.edu_tool_for_youtube.service.impl.SecurityServiceImpl;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class PlaylistController {
    @Autowired
    VideoService videoService;
    @Autowired
    PlaylistService playlistService;
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Transactional
    @RequestMapping(value = "/playlist/change", method = RequestMethod.GET)
    public String changePlaylist(Model model, @RequestParam("id") long id) {
        Video video = videoService.findById(id);
        Hibernate.initialize(video);

        Playlist playlistContainingTheVideo = playlistService.findByVideoId(video.getId());

        boolean currentValueOfCompleteness = video.isCompleted();
        if (currentValueOfCompleteness) {
            video.setCompleted(!video.isCompleted());
            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() - 1);

            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        } else {
            video.setCompleted(!video.isCompleted());
            ;
            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() + 1);

            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        }

        return "playlistPage";
    }

    @Transactional
    @RequestMapping(value = "/playlist/delete", method = RequestMethod.GET)
    public String deletePlaylist(Model model, @RequestParam("id") long playlistId) {
        playlistService.deleteById(playlistId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        Set<Playlist> playlistList = playlistService.findAllByUsers(user);

        model.addAttribute("listOfPlaylists", playlistList);
        return "pageWithListOfPlaylists";
    }
}