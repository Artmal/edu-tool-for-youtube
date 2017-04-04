package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class WelcomePageController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String addPlaylist(Model model, @RequestParam("addPlaylist_link") String link) throws IOException {
//        Document doc = Jsoup.connect(link).get();
//        Elements elements= doc.getElementsByTag("tr");
//        for(int i=0; i < elements.size(); i++){
//            String url=elements.get(i).attr("data-title");
//            System.out.println(url);
//        }

        User currentUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            currentUser = userService.findByUsername(name);
        }

        if(playlistService.findByLink(link) != null) {
            Playlist playlist = playlistService.findByLink(link);
            Set<User> users = playlist.getUsers();
            users.add(currentUser);
            playlist.setUsers(users);

            playlistService.save(playlist);

            Set<Playlist> playlistList = playlistService.findAllByUsers(currentUser);
            model.addAttribute("listOfPlaylists", playlistList);

            return "welcome";
        } else {
            //Get data about playlist through parsing html
            Document doc = Jsoup.connect(link).get();
            String playlistTitleWithYoutubeBenchmark = doc.getElementsByTag("title").first().text();
            String playlistTitle = playlistTitleWithYoutubeBenchmark.substring(0, playlistTitleWithYoutubeBenchmark.lastIndexOf("-"));
            String channelTitle = doc.select("a[data-ytid]").first().text();

            Set<User> users = new HashSet<>();
            users.add(currentUser);

            Playlist newPlaylist = new Playlist(playlistTitle, channelTitle, link, users);
            playlistService.save(newPlaylist);

            currentUser.getPlaylists().add(newPlaylist);
            userService.save(currentUser);

            Set<Playlist> playlistList = playlistService.findAllByUsers(currentUser);
            model.addAttribute("listOfPlaylists", playlistList);

            return "welcome";
        }
    }
}