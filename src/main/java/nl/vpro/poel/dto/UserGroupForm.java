package nl.vpro.poel.dto;

import nl.vpro.poel.domain.UserGroup;

import java.util.List;

public class UserGroupForm {

    private List<UserGroup> userGroups;

    public List<UserGroup> getUserGroups() { return userGroups; }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "UserGroupForm{" +
                "userGroups=" + userGroups +
                '}';
    }
}
