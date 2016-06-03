package nl.vpro.poel.dto;

import lombok.Data;

@Data
public class PredictionDTO {

    private Long predictionId;

    private Long matchId;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    private Long bonusId;

    private Long answerId;

    private boolean multiplier = false;
}
