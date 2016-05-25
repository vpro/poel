package nl.vpro.poel.dto;

import nl.vpro.poel.domain.UserGroup;

public class UserGroupRankingEntry {

    private final int rank;

    private final UserGroup userGroup;

    private final int score;

    public UserGroupRankingEntry(int rank, UserGroup userGroup, int score) {
        this.rank = rank;
        this.userGroup = userGroup;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroupRankingEntry that = (UserGroupRankingEntry) o;

        if (rank != that.rank) return false;
        if (score != that.score) return false;
        if (!userGroup.equals(that.userGroup)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + userGroup.hashCode();
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "UserGroupRankingEntry{" +
                "rank=" + rank +
                ", userGroup=" + userGroup +
                ", score=" + score +
                '}';
    }
}
