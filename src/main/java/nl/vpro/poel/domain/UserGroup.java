package nl.vpro.poel.domain;

import javax.persistence.*;


@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public UserGroup() {} // For Hibernate

    public UserGroup(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroup userGroup = (UserGroup) o;

        if (id != null ? !id.equals(userGroup.id) : userGroup.id != null) return false;
        return name != null ? name.equals(userGroup.name) : userGroup.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
