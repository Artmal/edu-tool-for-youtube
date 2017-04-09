package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.PlaylistDao;
import com.artmal.edu_tool_for_youtube.dao.UserDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlaylistServiceImpl implements PlaylistService {
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
    public Playlist findById(long id) {
        return playlistDao.findById(id);
    }

    @Override
    public Set<Playlist> findAllByUsers(User user) {
        return playlistDao.findAllByUsers(user);
    }

    @Override
    public Playlist findByVideoId(long id) {
        List<Playlist> allPlaylist = playlistDao.findAll();
        for(Playlist playlist : allPlaylist) {
            for(Video video : playlist.getVideos()) {
                if(video.getId() == id) {
                    return playlist;
                }
            }
        }
        return null;
    }
}
