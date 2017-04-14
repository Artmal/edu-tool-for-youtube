package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;

import java.util.Set;

public interface PlaylistService {
    /**
     * @return id of playlist
     */
    long save(Playlist playlist);

    void deleteById(long id);

    Playlist findById(long id);
    Playlist findByVideoId(long id);

    Set<Playlist> findAllBySubject(Subject subject);
    Set<Playlist> findAllByUsers(User user);

//    Subject findSubjectOfThePlaylist(@Param("id") long id);
}
