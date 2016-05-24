package nl.vpro.poel.dto;


import java.util.List;

public class UsersForm {

    private List<UsersDTO> users;

    public List<UsersDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UsersDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersForm{" +
                "users=" + users +
                '}';
    }
}
