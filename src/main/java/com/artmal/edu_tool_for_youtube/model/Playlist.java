package com.artmal.edu_tool_for_youtube.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "channel")
    private String channel;

    @Column(name = "link")
    private String link;

    @Column(name = "amount_of_completed_videos")
    private int amountOfCompletedVideos = 0;

    @Column(name = "amount_of_videos")
    private int amountOfVideos;

    @ManyToMany(mappedBy = "playlists")
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist")
    private Set<Video> videos = new HashSet<>();

    public Playlist() {
    }

    public Playlist(String name, String channel, String link, Set<User> users) {
        this.name = name;
        this.channel = channel;
        this.link = link;
        this.users = users;
    }

    public Playlist(String name, String channel, String link, int amountOfVideos) {
        this.name = name;
        this.channel = channel;
        this.link = link;
        this.amountOfVideos = amountOfVideos;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public Set<Video> getVideos() {
        return videos;
    }
    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
    public int getAmountOfVideos() {
        return amountOfVideos;
    }
    public void setAmountOfVideos(int amountOfVideos) {
        this.amountOfVideos = amountOfVideos;
    }
    public int getAmountOfCompletedVideos() {
        return amountOfCompletedVideos;
    }
    public void setAmountOfCompletedVideos(int amountOfCompletedVideos) {
        this.amountOfCompletedVideos = amountOfCompletedVideos;
    }
}
