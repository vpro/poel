package nl.vpro.poel.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), "", AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) { this.user = user; }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

    public String getRealName() {
        return user.getRealName();
    }

    public String getGameName() {
        return user.getGameName();
    }

    public UserGroup getUserGroup() { return user.getUserGroup(); }
}
