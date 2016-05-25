package nl.vpro.poel.dto;

public class UserForm {

    private String realName;

    private String gameName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "realName='" + realName + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
