package nl.vpro.poel.domain;

import javax.persistence.*;


@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Group() {} // For Hibernate


    public Group( String name) {

        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}
