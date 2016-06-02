package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * A categorization of matches. The simplest form.
 */

@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class MatchDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public MatchDay(String name) { this.name = name; }
}
