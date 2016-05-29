package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String gameName;

    @ManyToOne
    private UserGroup userGroup;

    public User(String username, Role role, String realName, String gameName) {
        this(username, role, realName, gameName, null);
    }

    public User(String username, Role role, String realName, String gameName, UserGroup userGroup) {
        this.username = username;
        this.role = role;
        this.realName = realName;
        this.gameName = gameName;
        this.userGroup = userGroup;
    }
}
