package nl.vpro.poel.domain;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.Instant;

/**
 * A match for users to predict the outcome of. Matches can have limited periods in which they can be predicted.
 */
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column
    private Instant invalidAfter = null;

    @Column
    private Instant invalidBefore = null;

    private Match() {} // For Hibernate

    public Match(String text) {
        this.text = text;
    }

    public Match(String text, Instant invalidAfter, Instant invalidBefore) {
        this.text = text;
        this.invalidAfter = invalidAfter;
        this.invalidBefore = invalidBefore;
    }

    public String getText() {
        return text;
    }

    public boolean canBePredicatedAt(Instant instant) {
        return instant != null && checkAfter(instant) && checkBefore(instant);
    }

    private boolean checkAfter(Instant instant) {
        return invalidAfter == null || instant.isBefore(invalidAfter);
    }

    private boolean checkBefore(Instant instant) {
        return invalidBefore == null || instant.isAfter(invalidBefore);
    }
}
