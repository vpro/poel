package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Group;

import java.util.List;

public class GroupForm {

    private List<Group> groups;

    public List<Group> getGroups() { return groups; }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "GroupForm{" +
                "groups=" + groups +
                '}';
    }
}
