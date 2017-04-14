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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void addPlaylist(User user, Playlist playlist) {
        User currentUser = userDao.findByUsername(user.getUsername());
        Set<Playlist> playlistSet = currentUser.getPlaylists();
        playlistSet.add(playlist);
        userDao.save(currentUser);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void removeUserByUsername(String username) {
        userDao.removeUserByUsername(username);
    }

    @Override
    public User findByPlaylistsContaining(Playlist playlist) {
        return userDao.findByPlaylistsContaining(playlist);
    }

    @Override
    @Transactional(readOnly = true)
    public int findNumberOfPlaylists(String username) {
        User user = userDao.findByUsername(username);
        return user.getPlaylists().size();
    }
}