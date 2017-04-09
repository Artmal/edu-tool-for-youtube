package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PlaylistDao extends JpaRepository<Playlist, Long>{
    Playlist findByLink(String link);

    Playlist findById(long id);

    Set<Playlist> findAllByUsers(User user);

}
