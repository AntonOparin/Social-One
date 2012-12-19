package com.socialone.social;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.google.common.eventbus.EventBus;
import com.socialone.event.connection.ConnectionAddedEvent;

public final class SimpleSignInAdapter implements SignInAdapter {
    @Inject
    private EventBus eventBus;

    private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse.class));
        eventBus.post(new ConnectionAddedEvent(userId, connection));
        return null;
    }

}