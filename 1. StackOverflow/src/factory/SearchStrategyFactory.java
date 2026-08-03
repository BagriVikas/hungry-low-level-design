package factory;

import entity.Tag;
import entity.User;
import strategy.search.KeywordSearchStrategy;
import strategy.search.SearchStrategy;
import strategy.search.TagSearchStrategy;
import strategy.search.UserSearchStrategy;

public class SearchStrategyFactory {

    public static SearchStrategy getKeywordSearchStrategy(String keyword) {
        return new KeywordSearchStrategy(keyword);
    }

    public static SearchStrategy getTagSearchStrategy(Tag tag) {
        return new TagSearchStrategy(tag);
    }

    public static SearchStrategy getUserSearchStrategy(User user) {
        return new UserSearchStrategy(user);
    }

}
