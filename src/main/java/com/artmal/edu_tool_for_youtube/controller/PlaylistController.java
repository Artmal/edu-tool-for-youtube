package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {
    @Autowired
    VideoService videoService;
    @Autowired
    PlaylistService playlistService;
    
    @RequestMapping(value = "/playlist/changeVideoCompleteness", method = RequestMethod.GET)
    public String changePlaylist(Model model, @RequestParam("video_id") long videoId) {
        videoService.changeValueOfCompleteness(model, videoId);
        return "playlistPage";
    }

    @RequestMapping(value = "/playlist/deletePlaylist", method = RequestMethod.GET)
    public String deletePlaylist(Model model, @RequestParam("playlist_id") long playlistId) {
        playlistService.deleteById(model, playlistId);
        return "pageWithListOfPlaylists";
    }
}