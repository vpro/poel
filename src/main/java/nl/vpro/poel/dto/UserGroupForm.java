package nl.vpro.poel.dto;

import java.util.List;

public class UserGroupForm {

    private List<UserGroupDTO> userGroups;

    public List<UserGroupDTO> getUserGroups() { return userGroups; }

    public void setUserGroups(List<UserGroupDTO> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "UserGroupForm{" +
                "userGroups=" + userGroups +
                '}';
    }
}
