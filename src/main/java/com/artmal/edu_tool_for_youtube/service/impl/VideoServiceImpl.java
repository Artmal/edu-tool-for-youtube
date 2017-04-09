package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.VideoDao;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;

    @Override
    public void save(Video video) {
        videoDao.save(video);
    }

    @Override
    public List<Video> getAllByPlaylistId(long playlistId) {
        return videoDao.getAllByPlaylistId(playlistId);
    }

    @Override
    public Video findById(long id) {
        return videoDao.findById(id);
    }
}
