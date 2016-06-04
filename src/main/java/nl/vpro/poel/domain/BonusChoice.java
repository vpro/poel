package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class BonusChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = true)
    private BonusCategory category;

    @Column(nullable = false, unique = true)
    private String value;

    public BonusChoice(String value, BonusCategory category) {
        this.value = value;
        this.category = category;
    }
}
