package entity;

import java.util.Objects;

public class Tag {

    private final String title;

    public Tag (String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        return title.equals(((Tag) obj).title);

    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}
