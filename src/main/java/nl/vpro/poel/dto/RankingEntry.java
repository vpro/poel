package nl.vpro.poel.dto;

import lombok.Data;

@Data
public class RankingEntry<T> {

    private final int rank;

    private final T subject;

    private final double score;
}
