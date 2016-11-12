package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/17/16
 */
@Service
public class MealPlanService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MealDaoImpl mealDao;

    @Autowired
    private UserDaoImpl userDao;

    @Transactional
    public List<Dish> getDishList() {
        List<Dish> dishList = mealDao.getDishList();

        return dishList;
    }

    @Transactional
    public Meal getBreakfastForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("B")) {
                return meal;
            }
        }

        return null;
    }

    @Transactional
    public Meal getLunchForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("L")) {
                return meal;
            }
        }

        return null;
    }

    @Transactional
    public Meal getMealForUserForDay(User user, String day, String mealType) {
        if (mealType.equals("B")) {
            return getBreakfastForUserForDay(user, day);
        } else if (mealType.equals("L")) {
            return getLunchForUserForDay(user, day);
        }

        return null;
    }

    @Transactional
    public void updateMealPlanForUser(Meal newMeal, Meal existingMeal, User user) {

        userDao.updateMealForUser(user, newMeal, existingMeal);
    }

    @Transactional
    public void insertNewDish(Dish dish) {
        mealDao.insertNewDish(dish);
    }

    @Transactional
    public List<Dish> getDishListByMeal(Meal meal) {
        return meal.getMealDishes();
    }

    @Transactional
    public void deleteMeal(List<Meal> selectedMealList) {
        mealDao.deleteMeal(selectedMealList);
    }

    @Transactional
    public String getMealDishesAsString(Meal meal) {
        String dishes = "";
        for (Dish dish : meal.getMealDishes()) {
            dishes += dish.getName() + ",";
        }

        return dishes.substring(0, dishes.length() - 1);
    }

    @Transactional
    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser() {
        List<Meal> userMealList = userDetailsService.getAdminMealList();
        Map<String, Map<String, Meal>> dayMealMap = new HashMap<String, Map<String, Meal>>();
        for (Meal userMeal : userMealList) {
            Map<String, Meal> mealMap = dayMealMap.get(userMeal.getDay());
            if (mealMap == null) {
                mealMap = new HashMap<>();
            }
            mealMap.put(userMeal.getType(), userMeal);
            dayMealMap.put(userMeal.getDay(), mealMap);
        }

        return dayMealMap;
    }
}
