package net.therap.mealplanner.service;

import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.web.security.AuthUser;

import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 11/16/16
 */
public interface MealPlanService {

    public List<Dish> getDishList();

    public void updateMealPlanForUser(List<Integer> dishIdList, AuthUser user, Meal newMeal);

    public void insertNewDish(Dish dish);

    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser();
}
