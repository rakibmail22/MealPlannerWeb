package net.therap.mealplanner.web.helper;

import net.therap.mealplanner.domain.Day;
import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.MealType;
import org.springframework.stereotype.Component;

/**
 * @author bashir
 * @since 11/15/16
 */
@Component
public class MealDishHelper {

    public MealType getMealTypeByAction(String actionName) {

        if ("Update Breakfast".equals(actionName)) {
            return MealType.B;
        } else if ("Update Lunch".equals(actionName)) {
            return MealType.L;
        }

        return null;
    }

    public Meal createNewMeal(String day, String actionName) {
        Meal meal = new Meal();
        meal.setDay(Day.valueOf(day));
        meal.setType(getMealTypeByAction(actionName));
        return meal;
    }

    public Dish createNewDish(String dishName) {

        Dish dish = new Dish();
        dish.setName(dishName);

        return dish;
    }
}
