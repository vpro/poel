package nl.vpro.poel.dto;

import nl.vpro.poel.domain.User;

public class RankingEntry {

    private final User user;

    private final int score;

    public RankingEntry(User user, int score) {
        this.user = user;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }
}
