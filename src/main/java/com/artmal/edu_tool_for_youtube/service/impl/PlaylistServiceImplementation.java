package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.PlaylistDao;
import com.artmal.edu_tool_for_youtube.dao.UserDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaylistServiceImplementation implements PlaylistService {
    @Autowired
    private PlaylistDao playlistDao;

    @Override
    public long save(Playlist playlist) {
        if(playlistDao.findByLink(playlist.getLink()) != null) {
            return playlist.getId();
        } else {
            playlistDao.save(playlist);
            return playlistDao.findByLink(playlist.getLink()).getId();
        }
    }

    @Override
    public Playlist findByLink(String link) {
        return playlistDao.findByLink(link);
    }

    @Override
    public Set<Playlist> findAllByUsers(User user) {
        return playlistDao.findAllByUsers(user);
    }
}
