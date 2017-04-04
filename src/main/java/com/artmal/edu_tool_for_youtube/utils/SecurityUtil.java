package com.artmal.edu_tool_for_youtube.utils;

import com.artmal.edu_tool_for_youtube.model.User;
import com.artmal.edu_tool_for_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    @Autowired
    private static UserService userService;

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            User user = userService.findByUsername(name);
            return user;
        }

        return null;
    }
}
