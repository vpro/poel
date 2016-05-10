package nl.vpro.poel.domain;

import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

    @Test
    public void withoutTimeConstraintsIsValid() {
        Match match = new Match("Why?");

        boolean validNow = match.canBePredicatedAt(Instant.now());

        assertThat(validNow).isTrue();
    }

    @Test
    public void onlyInvalidAfterConstraint() {
        Match match = new Match("How?", Instant.ofEpochSecond(200L), null);

        boolean validBefore = match.canBePredicatedAt(Instant.ofEpochSecond(100L));
        assertThat(validBefore).isTrue();

        boolean validAfter = match.canBePredicatedAt(Instant.ofEpochSecond(300L));
        assertThat(validAfter).isFalse();
    }

    @Test
    public void onlyInvalidBeforeConstraint() {
        Match match = new Match("Where?", null, Instant.ofEpochSecond(200L));

        boolean validBefore = match.canBePredicatedAt(Instant.ofEpochSecond(100L));
        assertThat(validBefore).isFalse();

        boolean validAfter = match.canBePredicatedAt(Instant.ofEpochSecond(300L));
        assertThat(validAfter).isTrue();
    }

    @Test
    public void BothBeforeAndAfterConstraints() {
        Match match = new Match("When?", Instant.ofEpochSecond(400L), Instant.ofEpochSecond(200L));

        boolean validBefore = match.canBePredicatedAt(Instant.ofEpochSecond(100L));
        assertThat(validBefore).isFalse();

        boolean validDuring = match.canBePredicatedAt(Instant.ofEpochSecond(300L));
        assertThat(validDuring).isTrue();

        boolean validAfter = match.canBePredicatedAt(Instant.ofEpochSecond(500L));
        assertThat(validAfter).isFalse();
    }
}