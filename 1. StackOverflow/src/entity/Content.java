package entity;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Content {

    protected final String id;
    protected final String body;
    protected final User author;
    protected final LocalDateTime creationDate;

    public Content(String id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.creationDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
