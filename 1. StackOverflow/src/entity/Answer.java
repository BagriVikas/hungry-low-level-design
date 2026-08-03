package entity;

import java.util.UUID;

public class Answer extends Post {

    private boolean isAccepted = false;

    public Answer (String body, User author) {
        super(UUID.randomUUID().toString(), body, author);
    }

    public void setAccepted (boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

}
