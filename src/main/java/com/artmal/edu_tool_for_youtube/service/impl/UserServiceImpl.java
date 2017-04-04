package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.RoleDao;
import com.artmal.edu_tool_for_youtube.dao.UserDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Role;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Artem Malchenko
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void addPlaylist(User user, Playlist playlist) {
        User currentUser = userDao.findByUsername(user.getUsername());
        Set<Playlist> playlistSet = currentUser.getPlaylists();
        playlistSet.add(playlist);
        userDao.save(currentUser);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}