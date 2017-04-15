package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import org.springframework.ui.Model;

import java.util.List;

public interface VideoService  {
    List<Video> findAllByPlaylistId(long playlistId);
    List<Video> findAllByPlaylist(Playlist playlist);

    Video findById(long id);

    void removeAllByPlaylist(Playlist playlist);
    void save(Video video);

    void changeLevelOfUnderstanding(Model model, long videoId, int levelOfUnderstanding);
    String getVideoCode(Video video);

    void changeValueOfCompleteness(Model model, long videoId);

    void addNote(Model model, long videoId, String note);
}
