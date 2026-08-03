package enums;

public enum VoteType {

    UPVOTE(1),
    DOWNVOTE(-1);

    private final int val;

    private VoteType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

}
