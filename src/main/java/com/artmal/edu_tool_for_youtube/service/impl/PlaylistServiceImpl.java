package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.PlaylistDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoNoteService videoNoteService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private SubjectService subjectService;

    @Override
    @Transactional
    public long save(Playlist playlist, String subjectTitle) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());

        if(subjectService.findByTitle(subjectTitle) != null) {
            playlist.setSubject(subjectService.findByTitle((subjectTitle)));
        } else {
            Subject subject = new Subject(subjectTitle, loggedInUser);
            playlist.setSubject(subject);
        }


        if(playlistDao.findByLink(playlist.getLink()) != null) {
            return playlist.getId();
        } else {
            loggedInUser.getPlaylists().add(playlist);
            playlistDao.save(playlist);
            return playlistDao.findByLink(playlist.getLink()).getId();
        }
    }

    @Override
    @Transactional
    public void save(long playlistId) {
        Playlist playlist = playlistDao.findById(playlistId);

        if(playlistDao.findByLink(playlist.getLink()) != null) {
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User loggedInUser = userService.findByUsername(auth.getName());
            loggedInUser.getPlaylists().add(playlist);
            playlistDao.save(playlist);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist findById(long id) {
        return playlistDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Playlist> findAllByUsers(User user) {
        return playlistDao.findAllByUsers(user);
    }


    @Override
    @Transactional
    public List<String> initializeListOfSubjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());

        Set<Playlist> playlistList = playlistDao.findAllByUsers(loggedInUser);

        List<String> videosSubjects = new ArrayList<>();
        for(Playlist p : playlistList) {
            videosSubjects.add(p.getSubject().getTitle());
        }

        return videosSubjects;
    }

    @Override
    public void removeById(long id) {
        playlistDao.removeById(id);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional
    public void deleteById(Model model, long playlistId) {
        Playlist playlist = playlistDao.findById(playlistId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        //Delete all notes which are associated with the videos of the playlist
        List<Video> videosOfThePlaylist = videoService.findAllByPlaylist(playlist);
        for(Video video: videosOfThePlaylist) {
            videoNoteService.removeAllByVideo(video);
        }

        videoService.removeAllByPlaylist(playlist);

        currentUser.getPlaylists().remove(playlist);
        playlistDao.removeById(playlistId);

        Set<Playlist> playlistList = playlistDao.findAllByUsers(currentUser);
        model.addAttribute("listOfPlaylists", playlistList);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Playlist> findAllBySubject(Subject subject) {
        return playlistDao.findAllBySubject(subject);
    }
}
