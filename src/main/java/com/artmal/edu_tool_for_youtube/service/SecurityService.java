package com.artmal.edu_tool_for_youtube.service;

/**
 * Service for Security.
 *
 * @author Artem Malchenko
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
