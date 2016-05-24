package nl.vpro.poel.dto;

public class UsersDTO {

    private Long userGroupId;

    private Long userId;

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "userGroupId=" + userGroupId +
                ", userId=" + userId +
                '}';
    }
}


