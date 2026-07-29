package strategy;

import entity.Question;
import entity.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearchStrategy implements SearchStrategy{

    private final Tag tag;

    public TagSearchStrategy(Tag tag) {
        this.tag = tag;
    }

    @Override
    public List<Question> filter(List<Question> questions) {

        return questions.stream()
                .filter(question -> question.getTags().contains(tag))
                .collect(Collectors.toList());

    }

}
