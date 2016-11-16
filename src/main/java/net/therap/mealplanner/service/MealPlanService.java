package net.therap.mealplanner.service;

import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 11/16/16
 */
public interface MealPlanService {

    public List<Dish> getDishList();

    public Meal getBreakfastForUserForDay(User user, String day);

    public Meal getLunchForUserForDay(User user, String day);

    public Meal getMealForUserForDay(User user, String day, String mealType);

    public void updateMealPlanForUser(List<Integer> dishIdList, User user, Meal newMeal);

    public void insertNewDish(Dish dish);

    public List<Dish> getDishListByMeal(Meal meal);

    public void deleteMeal(List<Meal> selectedMealList);

    public String getMealDishesAsString(Meal meal);

    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser();
}
