package net.therap.mealplanner.service;

import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;

import java.util.List;

/**
 * @author bashir
 * @since 11/16/16
 */
public interface UserDetailsService {

    public List<Meal> getMealListByUser(User user);

    public User validateUser(String email, String password);

    public User addNewUser(User user);

    public List<Meal> getAdminMealList();
}
