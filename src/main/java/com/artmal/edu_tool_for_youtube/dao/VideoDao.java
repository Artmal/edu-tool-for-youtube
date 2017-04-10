package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoDao extends JpaRepository<Video, Long> {
    List<Video> getAllByPlaylistId(long playlistId);

    Video findById(long id);

    void removeAllByPlaylist(Playlist playlist);
    List<Video> findAllByPlaylist(Playlist playlist);
}
