import entity.Answer;
import entity.Question;
import entity.Tag;
import entity.User;
import enums.VoteType;

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
        Tag javaTag = new Tag("java");
        Tag designPatternsTag = new Tag("design-patterns");
        Set<Tag> tags = Set.of(javaTag, designPatternsTag);
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

    }

    private static void printReputations(User... users) {
        System.out.println("--- Current Reputations ---");
        for(User user : users) {
            System.out.printf("%s: %d\n", user.getName(), user.getReputation());
        }
    }

}
