package nl.vpro.poel.dto;


import nl.vpro.poel.domain.User;

public class UserForm {

    private User updateUser;

    public User getUpdateUser() { return updateUser; }

    public void setUpdateUser( User updateUser ) { this.updateUser = updateUser; }

    @Override
    public String toString() {
        return "UserForm{" +
                "updateUser=" + updateUser +
                '}';
    }
}
