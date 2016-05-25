package nl.vpro.poel.dto;

public class UserAndUserGroupDTO {

    private Long userId;

    private Long userGroupId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public String toString() {
        return "UserAndUserGroupDTO{" +
                "userId=" + userId +
                ", userGroupId=" + userGroupId +
                '}';
    }
}


