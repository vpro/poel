package nl.vpro.poel.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersForm {

    private List<UserAndUserGroupDTO> users;
}
