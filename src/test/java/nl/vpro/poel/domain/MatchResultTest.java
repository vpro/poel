package nl.vpro.poel.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {

    @Test
    public void home() {
        MatchResult result = new MatchResult(5, 3);
        assertThat(result.getWinner()).isEqualTo(Winner.HOME);
    }

    @Test
    public void equal() {
        MatchResult result = new MatchResult(2, 2);
        assertThat(result.getWinner()).isEqualTo(Winner.NEITHER);
    }

    @Test
    public void away() {
        MatchResult result = new MatchResult(0, 1);
        assertThat(result.getWinner()).isEqualTo(Winner.AWAY);
    }
}