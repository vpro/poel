package nl.vpro.poel.dto;

public class RankingEntry<T> {

    private final int rank;

    private final T subject;

    private final int score;

    public RankingEntry(int rank, T subject, int score) {
        this.rank = rank;
        this.subject = subject;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public T getSubject() {
        return subject;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankingEntry<?> that = (RankingEntry<?>) o;

        if (rank != that.rank) return false;
        if (score != that.score) return false;
        return subject != null ? subject.equals(that.subject) : that.subject == null;

    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "RankingEntry{" +
                "rank=" + rank +
                ", subject=" + subject +
                ", score=" + score +
                '}';
    }
}
