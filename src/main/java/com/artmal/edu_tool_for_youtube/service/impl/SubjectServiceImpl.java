package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.SubjectDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private PlaylistService playlistService;

    @Override
    @Transactional(readOnly = true)
    public Set<Subject> findAllByUser(User user) {
        return subjectDao.findAllByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findById(long id) {
        return subjectDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findByTitle(String title) {
        return subjectDao.findByTitle(title);
    }

    @Override
    @Transactional
    public void removeByPlaylistId(long playlistId) {
        Playlist playlist = playlistService.findById(playlistId);
        subjectDao.delete(playlist.getSubject().getId());
    }

}
