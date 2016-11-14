package net.therap.mealplanner.web.command;

/**
 * @author bashir
 * @since 11/14/16
 */
public class LoginFormInfo {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "Username: "+username+" Password: "+password;
    }
}
