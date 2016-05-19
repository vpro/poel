package nl.vpro.poel.dto;

import nl.vpro.poel.domain.User;

public class RankingEntry {

    private final int rank;

    private final User user;

    private final int score;

    public RankingEntry(int rank, User user, int score) {
        this.rank = rank;
        this.user = user;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public User getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }
}
