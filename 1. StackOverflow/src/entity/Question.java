package entity;

import enums.EventType;

import java.util.*;

public class Question extends Post {

    private final String title;
    private final Set<Tag> tags;
    private final List<Answer> answers;
    private Answer acceptedAnswer;

    public Question (String title, String body, User author, Set<Tag> tags) {
        super(UUID.randomUUID().toString(), body, author);
        this.title = title;
        this.tags = tags;
        answers = new ArrayList<>();
    }

    public void addAnswer (Answer answer) {
        answers.add(answer);
    }

    public void setAcceptedAnswer (Answer acceptedAnswer) {

        // answer should be a part of 'this' question
        boolean isAnswerOfQuestion = false;
        for (Answer answer: answers) {
            if (answer.getId().equals(acceptedAnswer.getId())) {
                isAnswerOfQuestion = true;
                break;
            }
        }
        if (!isAnswerOfQuestion) {
            return;
        }
        Answer previousAcceptedAnswer = this.acceptedAnswer;
        this.acceptedAnswer = acceptedAnswer;
        acceptedAnswer.setAccepted(true);
        if (!acceptedAnswer.getAuthor().getId().equals(this.getAuthor().getId())) {
            notifyObservers(new Event(EventType.ACCEPT_ANSWER, this.author, this.acceptedAnswer));
        }
        if (null != previousAcceptedAnswer) {
            // unaccept previously accepted answer
            previousAcceptedAnswer.setAccepted(false);
            notifyObservers(new Event(EventType.UNACCEPT_ANSWER, this.author, previousAcceptedAnswer));
        }

    }

    public String getTitle() {
        return title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

}
