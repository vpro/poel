package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Match;

import java.util.List;

public class MatchForm {

    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "MatchForm{" +
                "matches=" + matches +
                '}';
    }
}
