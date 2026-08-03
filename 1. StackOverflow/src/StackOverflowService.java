import entity.*;
import enums.VoteType;
import observer.PostObserver;
import observer.ReputationManager;
import strategy.KeywordSearchStrategy;
import strategy.SearchStrategy;
import strategy.TagSearchStrategy;
import strategy.UserSearchStrategy;

import java.util.*;

public class StackOverflowService {

    private final Map<String, User> users;
    private final Map<String, Question> questions;
    private final Map<String, Answer> answers;
    private final PostObserver reputationManager;

    public StackOverflowService() {
        users = new HashMap<>();
        questions = new HashMap<>();
        answers = new HashMap<>();
        reputationManager = new ReputationManager();
    }

    public User createUser(String name) {

        User user = new User(name);
        users.put(user.getId(), user);
        return user;

    }

    public Question postQuestion(String userId, String title,
                                 String body, Set<Tag> tags) {

        User author = users.get(userId);
        Question question = new Question(title, body, author, tags);
        question.addObserver(reputationManager);
        questions.put(question.getId(), question);
        return question;

    }

    public Answer postAnswer(String userId, String questionId, String body) {

        User author = users.get(userId);
        Question question = questions.get(questionId);
        Answer answer = new Answer(body, author);
        answer.addObserver(reputationManager);
        question.addAnswer(answer);
        answers.put(answer.getId(), answer);
        return answer;

    }

    public Comment commentOnPost(String userId, String postId, String body) {

        User author = users.get(userId);
        Post post = questions.get(postId);
        if (null == post) {
            post = answers.get(postId);
        }
        Comment comment = new Comment(body, author);
        post.addComment(comment);
        return comment;

    }

    public void voteOnPost(String userId, String postId, VoteType type) {

        User voter = users.get(userId);
        Post post = questions.get(postId);
        if (null == post) {
            post = answers.get(postId);
        }
        post.vote(voter, type);

    }

    public List<Question> searchQuestionsByKeyword(String keyword) {

        SearchStrategy questionsByKeywordStrategy = new KeywordSearchStrategy(keyword);
        return questionsByKeywordStrategy.filter((List<Question>) questions.values());

    }

    public List<Question> searchQuestionsByTag(String tag) {

        Tag tagObj = new Tag(tag);
        SearchStrategy questionsByTagStrategy = new TagSearchStrategy(tagObj);
        return questionsByTagStrategy.filter((List<Question>) questions.values());

    }

    public List<Question> searchQuestionsByUser(String userId) {

        User author = users.get(userId);
        SearchStrategy questionsByUserStrategy = new UserSearchStrategy(author);
        return questionsByUserStrategy.filter((List<Question>) questions.values());

    }

    public void acceptAnswer(String questionId, String answerId) {

        Question question = questions.get(questionId);
        Answer answer = answers.get(answerId);
        question.setAcceptedAnswer(answer);

    }

}
