package net.therap.mealplanner.web.security;

import net.therap.mealplanner.domain.Role;

/**
 * @author bashir
 * @since 11/17/16
 */
public class AuthUser {

    private int userId;
    private Role userRole;
    
    public AuthUser(int userId, Role role) {

        this.userId = userId;
        this.userRole = role;
    }

    public AuthUser(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
