package com.artmal.edu_tool_for_youtube.model;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents a Subject(like Programming, Philosophy).
 *
 * @author Artem Malchenko
 * @version 1.0
 */

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Subject() {
    }

    public Subject(String title, User user) {
        this.title = title;
        this.user = user;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
