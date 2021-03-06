package com.artmal.edu_tool_for_youtube.dao;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Subject;
import com.artmal.edu_tool_for_youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PlaylistDao extends JpaRepository<Playlist, Long>{
    Playlist findByLink(String link);
    Playlist findById(long id);

    Set<Playlist> findAllByUsers(User user);
    Set<Playlist> findAllBySubject(Subject subject);

    @Modifying
    @Query("delete from Playlist p WHERE p.id = (:id)")
    void removeById(@Param("id") long id);
}
