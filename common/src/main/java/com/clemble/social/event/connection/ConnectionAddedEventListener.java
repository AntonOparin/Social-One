package com.clemble.social.event.connection;

import com.google.common.eventbus.Subscribe;

public interface ConnectionAddedEventListener<T> {

    @Subscribe
    public T connectionAddedEvent(ConnectionAddedEvent connectionAddedEvent);

}
