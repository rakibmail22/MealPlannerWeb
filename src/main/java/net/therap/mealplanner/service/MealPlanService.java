package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserDetailsService userDetailsService;
    @Autowired
    MealDaoImpl mealDao;
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    public List<Dish> getDishList() {
        List<Dish> dishList = mealDao.getDishList();
        return dishList;
    }


    public List<Meal> getAllMealOfType(String type, String day) {
        List<Meal> mealList = mealDao.getAllMealListOfTypeAndDay(type, day);
        return mealList;
    }

    public Meal getBreakfastForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("B")) {
                return meal;
            }
        }
        return null;
    }

    public Meal getLunchForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("L")) {
                return meal;
            }
        }
        return null;
    }

    public Meal getMealForUserForDay(User user, String day, String mealType) {
        if (mealType.equals("B")) {
            return getBreakfastForUserForDay(user, day);
        } else if (mealType.equals("L")) {
            return getLunchForUserForDay(user, day);
        }
        return null;
    }

    public int updateMealPlanForUser(Meal newMeal, Meal existingMeal, User user) {
        int result = 0;
        result = mealDao.updateMealForUser(existingMeal, newMeal, user);
        return result;
    }

    public void insertNewMealPlanForUser(List<Dish> dishIdList, String day, String mealType, User user) {
        Meal meal = new Meal();
        meal.setDay(day);
        meal.setType(mealType);
        mealDao.insertMealForUser(meal, dishIdList, user);
    }

    public void insertNewDish(Dish dish) {
        mealDao.insertNewDish(dish);
    }

    public int deleteMealForUser(Meal meal, User user) {
        return mealDao.deleteMealForUser(meal, user);
    }

    public List<Dish> getDishListByMeal(Meal meal) {
        return mealDao.getDishListByMeal(meal);
    }

    public List<Meal> getAllMeal() {
        return mealDao.getAllMeal();
    }

    public void createNewMeal(List<Dish> dishList, String type) {
        mealDao.createNewMeal(dishList, type);
    }

    public void deleteMeal(List<Meal> selectedMealList) {
        mealDao.deleteMeal(selectedMealList);
    }

    public String getMealDishesAsString(Meal meal) {
        String dishes = "";
        for (Dish dish : getDishListByMeal(meal)) {
            dishes += dish.getName() + ",";
        }
        return dishes.substring(0, dishes.length() - 1);
    }

    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser() {
        List<Meal> userMealList = userDetailsService.getAdminMealList();
        Map<String, Map<String, Meal>> dayMealMap = new HashMap<String, Map<String, Meal>>();
        for (Meal userMeal : userMealList) {
            Map<String, Meal> mealMap = dayMealMap.get(userMeal.getDay());
            if (mealMap == null) {
                mealMap = new HashMap<String, Meal>();
            }
            mealMap.put(userMeal.getType(), userMeal);
            dayMealMap.put(userMeal.getDay(), mealMap);
        }
        return dayMealMap;
    }
}
