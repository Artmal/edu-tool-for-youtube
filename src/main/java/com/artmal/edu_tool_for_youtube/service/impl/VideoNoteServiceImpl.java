package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.VideoNoteDao;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;
import com.artmal.edu_tool_for_youtube.service.VideoNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoNoteServiceImpl implements VideoNoteService {
    @Autowired
    private VideoNoteDao videoNoteDao;

    @Override
    public void save(VideoNote videoNote) {
        videoNoteDao.save(videoNote);
    }

    @Override
    public List<VideoNote> findAllByVideo(Video video) {
        return videoNoteDao.findAllByVideo(video);
    }

    @Override
    public void removeAllByVideo(Video video) {
        videoNoteDao.removeAllByVideo(video);
    }
}
