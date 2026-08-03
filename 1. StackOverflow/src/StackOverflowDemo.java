import entity.Answer;
import entity.Question;
import entity.Tag;
import entity.User;
import enums.VoteType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StackOverflowDemo {

    public static void main(String[] args) {

        StackOverflowService service = new StackOverflowService();

        // 1. Create Users
        User alice = service.createUser("Alice");
        User bob = service.createUser("Bob");
        User charlie = service.createUser("Charlie");

        // 2. Alice posts a question
        System.out.println("--- Alice posts a question ---");
        List<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("design-patterns");
        Question question = service.postQuestion(alice.getId(), "How to implement Observer Pattern?", "Details about Observer Pattern...", tags);
        printReputations(alice, bob, charlie);

        // 3. Bob and Charlie post answers
        System.out.println("\n--- Bob and Charlie post answers ---");
        Answer bobAnswer = service.postAnswer(bob.getId(), question.getId(), "You can use the java.util.Observer interface.");
        Answer charlieAnswer = service.postAnswer(charlie.getId(), question.getId(), "A better way is to create your own Observer interface.");
        printReputations(alice, bob, charlie);

        // 4. Voting happens
        System.out.println("\n--- Voting Occurs ---");
        service.voteOnPost(alice.getId(), question.getId(), VoteType.UPVOTE); // Alice upvotes her own question; no change in reputation
        service.voteOnPost(bob.getId(), charlieAnswer.getId(), VoteType.UPVOTE); // Bob upvotes Charlie's answer; +10 in charlie's reputation
        service.voteOnPost(alice.getId(), bobAnswer.getId(), VoteType.DOWNVOTE); // Alice downvotes Bob's answer; -2 for Bob, -1 for Alice
        printReputations(alice, bob, charlie);

        // 5. Alice accepts Charlie's answer
        System.out.println("\n--- Alice accepts Charlie's answer ---");
        service.acceptAnswer(question.getId(), charlieAnswer.getId()); // Charlie's reputation gets +15 and becomes 25
        printReputations(alice, bob, charlie);

        // 6. Alice changes accepted answer with Bob's answer
        System.out.println("\n--- Alice accepts Bob's answer ---");
        service.acceptAnswer(question.getId(), bobAnswer.getId()); // charlie' reputation = 25 - 15 = 10, and Bob's reputation = -2 + 15 = 13
        printReputations(alice, bob, charlie);

        // 7. Alice commented on 'Bob's answer
        System.out.println("\n--- Alice comments on Bob's answer ---");
        service.commentOnPost(alice.getId(), bobAnswer.getId(), "Using Observer provided by Java is really easy to understand and implement");
        printReputations(alice, bob, charlie); // no change in reputation of any user

        // 8. Search questions by keyword
        System.out.println("\n--- Searching questions by keyword ---");
        List<Question> questionsByKeyword = service.searchQuestionsByKeyword("observer");
        System.out.println("\n--- Questions by keyword: " + questionsByKeyword.size());

        // 9. Search questions by 'tag'
        System.out.println("\n--- Searching questions by tag ---");
        List<Question> questionsByTag = service.searchQuestionsByTag("design-patterns");
        System.out.println("\n--- Questions by tag: " + questionsByTag.size());

        // 10. Search questions by 'user'
        System.out.println("\n--- Searching questions by user ---");
        List<Question> questionsByUser = service.searchQuestionsByUser(alice.getId());
        System.out.println("\n--- Questions by user: " + questionsByUser.size());

    }

    private static void printReputations(User... users) {
        System.out.println("--- Current Reputations ---");
        for(User user : users) {
            System.out.printf("%s: %d\n", user.getName(), user.getReputation());
        }
    }

}
