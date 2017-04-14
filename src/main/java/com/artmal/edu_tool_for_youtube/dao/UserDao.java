package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    void removeUserByUsername(String username);

    User findByPlaylistsContaining(Playlist playlist);
    User findByUsername(String username);
}
