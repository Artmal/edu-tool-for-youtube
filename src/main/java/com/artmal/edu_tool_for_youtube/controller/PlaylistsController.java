package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import com.artmal.edu_tool_for_youtube.utils.HtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = {"/", "/list-of-playlists"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());

        Set<Playlist> playlistList = playlistService.findAllByUsers(loggedInUser);
        List<String> videosSubjects = playlistService.initializeListOfSubjects();

        model.addAttribute("listOfSubjects", videosSubjects);
        model.addAttribute("listOfPlaylists", playlistList);
        return "pageWithListOfPlaylists";
    }

    @RequestMapping(value = "/list-of-playlists", method = RequestMethod.POST)
    public String addPlaylist(Model model, @RequestParam("addPlaylist_link") String link,
                              @RequestParam("addPlaylist_subject") String subjectTitle) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());

        Playlist newPlaylist = HtmlParser.initializePlaylist(link);
        playlistService.save(newPlaylist, subjectTitle);

        Document doc = Jsoup.connect(link).get();
        videoService.saveVideosForThePlaylist(doc, newPlaylist);

        Set<Playlist> playlistList = playlistService.findAllByUsers(loggedInUser);
        List<String> videosSubjects = playlistService.initializeListOfSubjects();

        model.addAttribute("listOfSubjects", videosSubjects);
        model.addAttribute("listOfPlaylists", playlistList);
        return "pageWithListOfPlaylists";
    }

    @RequestMapping(value = "/playlist", method = RequestMethod.GET)
    public String showPlaylist(Model model, @RequestParam("id") long playlistId) {
        List<Video> listOfVideos = videoService.findAllByPlaylistId(playlistId);
        model.addAttribute("listOfVideos", listOfVideos);
        model.addAttribute("playlistId", playlistId);
        return "playlistPage";
    }
}