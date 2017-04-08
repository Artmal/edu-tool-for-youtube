package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;

import java.util.Set;

public interface PlaylistService {
    //The method returns id of playlist
    long save(Playlist playlist);

    Playlist findByLink(String link);

    Set<Playlist> findAllByUsers(User user);
}