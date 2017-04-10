package com.artmal.edu_tool_for_youtube.service;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;

import java.util.List;

public interface VideoService  {
    void save(Video video);
    List<Video> getAllByPlaylistId(long playlistId);
    Video findById(long id);
    List<Video> findAllByPlaylist(Playlist playlist);
    void removeAllByPlaylist(Playlist playlist);
}
