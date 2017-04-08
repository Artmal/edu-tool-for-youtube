package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;

import java.util.Set;

/**
 * Service class for {@link com.artmal.edu_tool_for_youtube.model.User}
 *
 * @author Artem Malchenko
 * @version 1.0
 */

public interface UserService {
    void save(User user);

    void addPlaylist(User user, Playlist playlist);

    User findByUsername(String username);

    void removeUserByUsername(String username);
}
