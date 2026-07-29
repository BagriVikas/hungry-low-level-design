package entity;

import enums.EventType;

public class Event {

    private EventType eventType;
    private User actor;
    private Post targetPost;

    public Event(EventType eventType, User actor, Post targetPost) {
        this.eventType = eventType;
        this.actor = actor;
        this.targetPost = targetPost;
    }

    public EventType getEventType() {
        return eventType;
    }

    public User getActor() {
        return actor;
    }

    public Post getTargetPost() {
        return targetPost;
    }

}
