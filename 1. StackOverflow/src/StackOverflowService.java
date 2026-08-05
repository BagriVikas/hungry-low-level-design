import entity.*;
import enums.VoteType;
import factory.SearchStrategyFactory;
import observer.PostObserver;
import observer.ReputationManager;
import strategy.search.SearchStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// should not contain any synchronized method
// as we should allow client to call our method
// from a multithread environment
public class StackOverflowService {

    private final Map<String, User> users;
    private final Map<String, Question> questions;
    private final Map<String, Answer> answers;
    private final PostObserver reputationManager;

    public StackOverflowService() {
        users = new ConcurrentHashMap<>();
        questions = new ConcurrentHashMap<>();
        answers = new ConcurrentHashMap<>();
        reputationManager = new ReputationManager();
    }

    public User createUser(String name) {

        User user = new User(name);
        users.put(user.getId(), user);
        return user;

    }

    public Question postQuestion(String userId, String title,
                                 String body, List<String> tags) {

        User author = users.get(userId);
        Set<Tag> tagsSet = new HashSet<>();
        for (String tag: tags) {
            tagsSet.add(new Tag(tag));
        }
        Question question = new Question(title, body, author, tagsSet);
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

        SearchStrategy questionsByKeywordStrategy = SearchStrategyFactory.getKeywordSearchStrategy(keyword);
        List<Question> allQuestions = new ArrayList<>(questions.values());
        return questionsByKeywordStrategy.filter(allQuestions);

    }

    public List<Question> searchQuestionsByTag(String tag) {

        SearchStrategy questionsByTagStrategy = SearchStrategyFactory.getTagSearchStrategy(new Tag(tag));
        List<Question> allQuestions = new ArrayList<>(questions.values());
        return questionsByTagStrategy.filter(allQuestions);

    }

    public List<Question> searchQuestionsByUser(String userId) {

        User author = users.get(userId);
        SearchStrategy questionsByUserStrategy = SearchStrategyFactory.getUserSearchStrategy(author);
        List<Question> allQuestions = new ArrayList<>(questions.values());
        return questionsByUserStrategy.filter(allQuestions);

    }

    public void acceptAnswer(String questionId, String answerId) {

        Question question = questions.get(questionId);
        Answer answer = answers.get(answerId);
        question.setAcceptedAnswer(answer);

    }

}
