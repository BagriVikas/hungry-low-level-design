package strategy;

import entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordSearchStrategy implements SearchStrategy {

    private final String keyword;

    public KeywordSearchStrategy(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public List<Question> filter(List<Question> questions) {

        return questions.stream()
                .filter(question ->
                        question.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        question.getBody().toLowerCase().contains(keyword.toLowerCase())
                ).collect(Collectors.toList());

    }

}
