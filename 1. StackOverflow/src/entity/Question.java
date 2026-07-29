package entity;

import enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Question extends Post {

    private final String title;
    private final List<Tag> tags;
    private final List<Answer> answers;
    private Answer acceptedAnswer;

    public Question (String title, String body, User author, List<Tag> tags) {
        super(body, author);
        this.title = title;
        this.tags = tags;
        answers = new ArrayList<>();
    }

    public void addAnswer (Answer answer) {
        answers.add(answer);
    }

    public void setAcceptedAnswer (Answer acceptedAnswer) {

        // answer cannot be accepted if:
        // 1. both question and answer have the same author
        // 2. some other answer was already accepted before
        if (!this.getAuthor().getId().equals(acceptedAnswer.getAuthor().getId()) && null == this.acceptedAnswer) {
            this.acceptedAnswer = acceptedAnswer;
            acceptedAnswer.setAccepted(true);
            notifyObservers(new Event(EventType.ACCEPT_ANSWER, this.author, this.acceptedAnswer));
        }

    }

    public String getTitle() {
        return title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

}
