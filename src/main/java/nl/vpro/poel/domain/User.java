package nl.vpro.poel.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String gameName;

    private User() {} // For Hibernate

    public User(String username, Role role, String realName, String gameName) {
        this.username = username;
        this.role = role;
        this.realName = realName;
        this.gameName = gameName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public String getRealName() {
        return realName;
    }

    public String getGameName() {
        return gameName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (role != user.role) return false;
        return realName != null ? realName.equals(user.realName) : user.realName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", realName='" + realName + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
