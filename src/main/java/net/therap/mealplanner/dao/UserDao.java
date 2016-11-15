package net.therap.mealplanner.dao;

import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;

import java.util.List;

/**
 * @author bashir
 * @since 11/15/16
 */
public interface UserDao {

    public List<Meal> getMealListAdmin();

    public User getUserByEmail(String email);

    public User insertNewUser(User user);

    public User updateMealForUser(User user, Meal newMeal, Meal existingMeal);

    public List<Meal> getMealListByUser(User user);

    public User getUserById(int id);
}
