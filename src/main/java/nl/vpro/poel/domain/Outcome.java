package nl.vpro.poel.domain;

import javax.persistence.*;

/**
 * The actual outcome of a match.
 */
@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    private Match match;

    @Column(nullable = false)
    private String value;

    private Outcome() {} // For Hibernate

    public String getValue() {
        return value;
    }
}
