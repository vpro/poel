package nl.vpro.poel.dto;

import lombok.Data;

@Data
public class RankingEntry<T, S> {

    private final int rank;

    private final T subject;

    private final S score;
}
