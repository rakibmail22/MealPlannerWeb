package net.therap.mealplanner.web.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author bashir
 * @since 11/13/16
 */
public class SignUpFormInfo {

    @NotNull
    @Size(min = 1, message = "{name.required}")
    private String name;

    @NotNull
    @Size(min = 1, message = "{email.required}")
    private String email;

    @NotNull
    @Size(min = 1, message = "{password.required}")
    private String password;

    @NotNull
    @Size(min = 1, message = "{verifyPassword.required}")
    private String verifyPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String toString() {
        return "Name: " + name + " Email: " + email + " Password: " + password + " Verify Pass: " + verifyPassword;
    }
}
