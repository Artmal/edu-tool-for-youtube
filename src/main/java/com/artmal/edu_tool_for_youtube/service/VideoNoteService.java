package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;

import java.util.List;

public interface VideoNoteService {
    void save(VideoNote videoNote);

    List<VideoNote> findAllByVideo(Video video);
    void removeAllByVideo(Video video);
}
