package nl.vpro.poel.dto;

import java.util.List;

public class UsersForm {

    private List<UserAndUserGroupDTO> users;

    public List<UserAndUserGroupDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserAndUserGroupDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersForm{" +
                "users=" + users +
                '}';
    }
}
