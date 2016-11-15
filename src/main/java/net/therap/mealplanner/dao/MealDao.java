package net.therap.mealplanner.dao;

import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;

import java.util.List;

/**
 * @author bashir
 * @since 11/15/16
 */
public interface MealDao {

    public List<Dish> getDishList();

    public void insertNewDish(Dish dish);

    public void deleteMeal(List<Meal> selectedMealList);

    public void saveMeal(Meal meal);

    public List<Dish> getDishListById(List<Integer> idList);
}
