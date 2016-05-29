package nl.vpro.poel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MatchResult {

    @Column
    private int homeTeamGoals;

    @Column
    private int awayTeamGoals;

    @Override
    public String toString() {
        return homeTeamGoals + "-" + awayTeamGoals;
    }
}
