package nl.vpro.poel.dto;

import java.util.List;

public class MatchForm {

    private List<MatchDTO> matches;

    public List<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDTO> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "MatchForm{" +
                "matches=" + matches +
                '}';
    }
}
