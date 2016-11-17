package net.therap.mealplanner.domain;

/**
 * @author bashir
 * @since 11/17/16
 */
public enum Role {
    user("user"), admin("admin");

    private String role;

    Role(String role) {
        this.role=role;
    }

    public String getRole(){
        return role;
    }
}
