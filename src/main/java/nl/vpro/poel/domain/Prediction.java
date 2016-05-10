package nl.vpro.poel.domain;

import javax.persistence.*;

/**
 * A user's predicted outcome for a match.
 */
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    private Match match;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private String value;

    public Match getMatch() {
        return match;
    }

    public String getValue() {
        return value;
    }
}
