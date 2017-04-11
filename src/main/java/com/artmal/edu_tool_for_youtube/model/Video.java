package com.artmal.edu_tool_for_youtube.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Video from Youtube.
 *
 * @author Artem Malchenko
 * @version 1.0
 */

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "video_code")
    private String videoCode;

    @Column(name = "duration")
    private String duration;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
    private Set<VideoNote> notes = new HashSet<>();

    public Video() {
    }

    public Video(String title, String duration, String videoCode) {
        this.title = title;
        this.duration = duration;
        this.videoCode = videoCode;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Playlist getPlaylist() {
        return playlist;
    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public String getVideoCode() {
        return videoCode;
    }
    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }
    public Set<VideoNote> getNotes() {
        return notes;
    }
    public void setNotes(Set<VideoNote> notes) {
        this.notes = notes;
    }
}
