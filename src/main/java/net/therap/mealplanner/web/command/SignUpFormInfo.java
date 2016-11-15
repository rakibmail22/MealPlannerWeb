package net.therap.mealplanner.web.command;

/**
 * @author bashir
 * @since 11/13/16
 */
public class SignUpFormInfo {

    private String name;
    private String email;
    private String password;
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
