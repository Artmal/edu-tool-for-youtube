package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.PlaylistDao;
import com.artmal.edu_tool_for_youtube.dao.UserDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoNoteService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private VideoNoteService videoNoteService;
    @Autowired
    private VideoService videoService;

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

    @Override
    public void deleteById(long id) {
        Playlist playlist = playlistDao.findById(id);
        User currentUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            currentUser = userDao.findByUsername(name);
        }

        List<Video> videosOfThePlaylist = videoService.findAllByPlaylist(playlist);
        for(Video video: videosOfThePlaylist) {
            videoNoteService.removeAllByVideo(video);
        }

        videoService.removeAllByPlaylist(playlist);

        currentUser.getPlaylists().remove(playlist);
        playlistDao.delete(id);

    }
}
