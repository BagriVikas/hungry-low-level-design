package observer;

import entity.Event;
import enums.EventType;

public class ReputationManager implements PostObserver{

    private static final int QUESTION_UPVOTE_REP = 5;
    private static final int ANSWER_UPVOTE_REP = 10;
    private static final int ACCEPT_ANSWER_REP = 15;
    private static final int DOWNVOTED_POST_AUTHOR_PENALTY = -2;
    private static final int DOWNVOTER_PENALTY = -1;
    private static final int UNACCEPT_ANSWER_REP_PENALTY = -1 * ACCEPT_ANSWER_REP;

    @Override
    public void onPostEvent(Event event) {

        switch (event.getEventType()) {
            case UPVOTE_QUESTION:
                event.getTargetPost().getAuthor().updateReputation(QUESTION_UPVOTE_REP);
                break;
            case UPVOTE_ANSWER:
                event.getTargetPost().getAuthor().updateReputation(ANSWER_UPVOTE_REP);
                break;
            case DOWNVOTE_QUESTION, DOWNVOTE_ANSWER:
                event.getTargetPost().getAuthor().updateReputation(DOWNVOTED_POST_AUTHOR_PENALTY);
                event.getActor().updateReputation(DOWNVOTER_PENALTY);
                break;
            case ACCEPT_ANSWER:
                event.getTargetPost().getAuthor().updateReputation(ACCEPT_ANSWER_REP);
                break;
            case UNACCEPT_ANSWER:
                event.getTargetPost().getAuthor().updateReputation(UNACCEPT_ANSWER_REP_PENALTY);
                break;
        }

    }

}
