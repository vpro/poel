package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.User;

@Data
public class UserRankingEntry {

    private final int rank;

    private final User user;

    private final int score;
}