package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoNoteDao extends JpaRepository<VideoNote, Long> {
    List<VideoNote> findAllByVideo(Video video);
    void removeAllByVideo(Video video);
    void removeById(long id);
}
