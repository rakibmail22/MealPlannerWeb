package net.therap.mealplanner.web.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author bashir
 * @since 11/14/16
 */
public class LoginFormInfo {

    @NotNull
    @Size(min = 1, message = "{username.required}")
    String username;

    @NotNull
    @Size(min = 1, message = "{password.required}")
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

    public String toString() {
        return "Username: " + username + " Password: " + password;
    }
}
