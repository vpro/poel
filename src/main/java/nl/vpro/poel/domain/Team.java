package nl.vpro.poel.domain;

import javax.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Team() {} // For Hibernate

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
