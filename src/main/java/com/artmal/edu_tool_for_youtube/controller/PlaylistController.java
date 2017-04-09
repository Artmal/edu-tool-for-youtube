package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import com.artmal.edu_tool_for_youtube.service.impl.SecurityServiceImpl;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlaylistController {
    @Autowired
    VideoService videoService;

    @Autowired
    PlaylistService playlistService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Transactional
    @RequestMapping(value = "/playlist/change", method = RequestMethod.GET)
    public String changePlaylist(Model model, @RequestParam("id") long id) {
        Video video = videoService.findById(id);
        Hibernate.initialize(video);

        boolean currentValueOfCompleteness = video.isCompleted();
        if(currentValueOfCompleteness) {
            video.setCompleted(!video.isCompleted());
            Playlist playlist = playlistService.findByVideoId(video.getId());
            playlist.setAmountOfCompletedVideos(playlist.getAmountOfCompletedVideos() - 1);
            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlist.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        } else {
            video.setCompleted(!video.isCompleted());
            Playlist playlist = playlistService.findByVideoId(video.getId());
            playlist.setAmountOfCompletedVideos(playlist.getAmountOfCompletedVideos() + 1);
            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlist.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        }

        return "playlistPage";
    }
}