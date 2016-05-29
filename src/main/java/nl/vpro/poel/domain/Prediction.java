package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Objects;

/**
 * A user's predicted match result.
 */
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Match match;

    @Embedded
    private MatchResult matchResult;

    private boolean multiplier = false;

    public Prediction(User user, Match match) {
        this(user, match, null);
    }

    public Prediction(User user, Match match, MatchResult matchResult) {
        this(user, match, matchResult, false);
    }

    public Prediction(User user, Match match, MatchResult matchResult, boolean multiplier) {
        this.user = user;
        this.match = match;
        this.matchResult = matchResult;
        this.multiplier = multiplier;
    }
}
