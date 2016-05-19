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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankingEntry that = (RankingEntry) o;

        if (rank != that.rank) return false;
        if (score != that.score) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "RankingEntry{" +
                "rank=" + rank +
                ", user=" + user +
                ", score=" + score +
                '}';
    }
}
