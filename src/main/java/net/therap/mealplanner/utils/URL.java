package net.therap.mealplanner.utils;

/**
 * @author bashir
 * @since 11/17/16
 */
public enum URL {

    LOGIN("/login"),
    SIGNUP("/signup"),
    NOT_FOUND("/404"),
    LOGOUT("/logout"),
    UPDATE_MEAL("/meal/update"),
    ADD_DISH("/dish/add"),
    ADMIN_HOME("/admin/home"),
    USER_HOME("/home");

    String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
