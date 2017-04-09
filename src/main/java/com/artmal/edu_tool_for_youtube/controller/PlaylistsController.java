package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import com.artmal.edu_tool_for_youtube.utils.HtmlParser;
import org.hibernate.Hibernate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class PlaylistsController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    @Transactional
    @RequestMapping(value = "/list-of-playlists", method = RequestMethod.POST)
    public String addPlaylist(Model model, @RequestParam("addPlaylist_link") String link) throws IOException {
        User currentUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            currentUser = userService.findByUsername(name);
        }

        Playlist newPlaylist = HtmlParser.initializePlaylist(link);
        playlistService.save(newPlaylist);
        Hibernate.initialize(currentUser.getPlaylists());
        currentUser.getPlaylists().add(newPlaylist);

        Document doc = Jsoup.connect(link).get();
        List<String> videoTitles = HtmlParser.getVideoTitles(doc);
        List<String> durations = HtmlParser.getVideoDurations(doc);

        for(int i = 0; i < videoTitles.size(); i++) {
            Video video = new Video(videoTitles.get(i), durations.get(i));
            video.setPlaylist(newPlaylist);
            Hibernate.initialize(newPlaylist.getVideos());
            newPlaylist.getVideos().add(video);
            videoService.save(video);
        }

        Set<Playlist> playlistList = playlistService.findAllByUsers(currentUser);
        model.addAttribute("listOfPlaylists", playlistList);
        return "pageWithListOfPlaylists";
    }

    @RequestMapping(value = "/playlist", method = RequestMethod.GET)
    public String showPlaylist(Model model, @RequestParam("id") long id) {
        List<Video> listOfVideos = videoService.getAllByPlaylistId(id);
        model.addAttribute("listOfVideos", listOfVideos);
        return "playlistPage";
    }
}