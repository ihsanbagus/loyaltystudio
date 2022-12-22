package com.studio.loyalty.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.studio.loyalty.utils.LogUtils.LOGGER;

public class AccessDeniedUtils implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        String message = "User: " + auth.getName()
                + " attempted to access the protected URL: "
                + request.getRequestURI();
        LOGGER.warning(message);
        response.sendError(HttpStatus.FORBIDDEN.value(), message);
    }
}