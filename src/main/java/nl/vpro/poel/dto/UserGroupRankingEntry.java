package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.UserGroup;

@Data
public class UserGroupRankingEntry {

    private final int rank;

    private final UserGroup userGroup;

    private final double averageScore;
}