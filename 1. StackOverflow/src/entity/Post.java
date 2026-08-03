package entity;

import enums.EventType;
import enums.VoteType;
import observer.PostObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Post extends Content {

    private int voteCount = 0;
    private final Map<String, VoteType> voters = new HashMap<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<PostObserver> observers = new ArrayList<>();

    public Post(String id, String body, User author) {
        super(id, body, author);
    }

    public void addObserver(PostObserver postObserver) {
        observers.add(postObserver);
    }

    public void notifyObservers(Event event) {
        observers.forEach(obs -> obs.onPostEvent(event));
    }

    public void vote(User voter, VoteType voteType) {

        if (this.getAuthor().getId().equals(voter.getId())) {
            // user cannot vote on his own post
            return;
        }
        if (voters.get(voter.getId()) == voteType) {
            // already voted same
            return;
        }

        int voteChange = 0;
        if (voters.containsKey(voter.getId())) {
            // user wants to change the type of vote
            voteChange = VoteType.UPVOTE.equals(voteType) ? 2 * VoteType.UPVOTE.getVal() : 2 * VoteType.DOWNVOTE.getVal();
        } else {
            // user wants to give a fresh vote
            voteChange = VoteType.UPVOTE.equals(voteType) ? VoteType.UPVOTE.getVal() : VoteType.DOWNVOTE.getVal();
        }

        voters.put(voter.getId(), voteType);
        voteCount += voteChange;

        // emit appropriate event so that observers can perform the necessary actions
        // 4 possible vote events: UPVOTE_QUESTION, UPVOTE_ANSWER, DOWNVOTE_QUESTION, DOWNVOTE_ANSWER
        EventType eventType = null;
        if (this instanceof Question) {
            eventType = VoteType.UPVOTE.equals(voteType) ? EventType.UPVOTE_QUESTION : EventType.DOWNVOTE_QUESTION;
        } else {
            eventType = VoteType.UPVOTE.equals(voteType) ? EventType.UPVOTE_ANSWER : EventType.DOWNVOTE_ANSWER;
        }
        notifyObservers(new Event(eventType, voter, this));

    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
